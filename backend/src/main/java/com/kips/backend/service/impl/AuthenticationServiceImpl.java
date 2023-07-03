package com.kips.backend.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kips.backend.common.enums.Role;
import com.kips.backend.common.enums.TokenType;
import com.kips.backend.common.exception.GeneralException;
import com.kips.backend.common.util.ValidationUtil;
import com.kips.backend.config.JwtService;
import com.kips.backend.domain.ConfirmationToken;
import com.kips.backend.domain.Token;
import com.kips.backend.domain.User;
import com.kips.backend.repository.ConfirmationRepository;
import com.kips.backend.repository.TokenRepository;
import com.kips.backend.repository.UserRepository;
import com.kips.backend.service.AuthenticationService;
import com.kips.backend.service.EmailService;
import com.kips.backend.service.request.AuthenticationRequest;
import com.kips.backend.service.request.ConfirmationTokenRequest;
import com.kips.backend.service.request.RegisterRequest;
import com.kips.backend.service.response.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationServiceImpl.class.getName());


    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;
    private final ConfirmationRepository confirmationRepository;

    private Random rand;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {

        ValidationUtil.fieldCheckNullOrEmpty(request.getEmail(), "Email");

        if (repository.existsByEmail(request.getEmail())) {
            throw new GeneralException("This Email already in use");
        }

        if (request.getRole() == null) {
            request.setRole(Role.USER);
        }

        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .enabled(false)
                .createdTime(LocalDateTime.now())
                .build();

        var savedUser = repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        sendActivationEmail(savedUser, request.getEmail());

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .isEnabled(savedUser.isEnabled())
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            var user = repository.findByEmail(request.getEmail())
                    .orElseThrow();
            var jwtToken = jwtService.generateToken(user);
            var refreshToken = jwtService.generateRefreshToken(user);
            revokeAllUserTokens(user);
            saveUserToken(user, jwtToken);
            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                    .isEnabled(user.isEnabled())
                    .build();
        } catch (AuthenticationException ex) {
            checkLoginErrorCount(request.getEmail());
            throw new GeneralException(ex.getMessage());
        }
    }

    private void checkLoginErrorCount(String email) {
        Optional<User> optionalUser = repository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (Boolean.TRUE.equals(user.getEnabled())) {
                if (user.getLoginErrorCount() == null || user.getLoginErrorCount() < 5 ) {
                    Short loginErrorCount = user.getLoginErrorCount() == null ? 0 : user.getLoginErrorCount();
                    user.setLoginErrorCount((short) (loginErrorCount + 1));
                    user.setUpdatedTime(LocalDateTime.now());
                    repository.save(user);
                }
                if (user.getLoginErrorCount() == 5) {
                    user.setEnabled(false);
                    user.setUpdatedTime(LocalDateTime.now());
                    repository.save(user);
                }
            }
        }
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .value(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .createdTime(LocalDateTime.now())
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    @Override
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.repository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    @Override
    public Boolean enableUser(ConfirmationTokenRequest request) {
        Optional<User> userOptional = repository.findByEmail(request.getEmail());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Optional<ConfirmationToken> optionalConfirmationToken = confirmationRepository.findByTokenAndUser(request.getToken(), user);
            if (optionalConfirmationToken.isPresent()) {
                ConfirmationToken confirmationToken = optionalConfirmationToken.get();
                LocalDateTime nowMinus15Min = LocalDateTime.now().minusMinutes(15);
                if (confirmationToken.getCreatedTime().isBefore(nowMinus15Min)) {
                    throw new GeneralException("Token Expired");
                }
                if (confirmationToken.getToken().equals(request.getToken())) {
                    user.setEnabled(true);
                    repository.save(user);
                    return true;
                } else {
                    throw new GeneralException("Token did not match");
                }

            }
        }
        return false;
    }

    private void sendActivationEmail(User user, String to) {
        try {
            File file = ResourceUtils.getFile("classpath:templates/email_activation.html");
            String email = new String(Files.readAllBytes(file.toPath()));

            String randomNumString = String.valueOf(getRand().nextInt(999999) + 100000);

            var confirmationToken = ConfirmationToken.builder()
                    .token(randomNumString)
                    .user(user)
                    .createdTime(LocalDateTime.now())
                    .build();

            confirmationRepository.save(confirmationToken);

            email = email.replace("${name}", user.getFullName()).replace("${confirmationToken}", randomNumString);
            emailService.send(to, email);
        } catch (IOException | NoSuchAlgorithmException e) {
            LOGGER.info(String.format("Confirmation token not sent for User Id: %s",user.getId()));
            throw new GeneralException(e.getMessage());
        }
    }

    public Random getRand() throws NoSuchAlgorithmException {
        if (rand == null) {
            rand = SecureRandom.getInstanceStrong();
        }
        return rand;
    }
}