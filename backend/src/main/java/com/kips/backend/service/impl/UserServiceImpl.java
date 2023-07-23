package com.kips.backend.service.impl;

import com.kips.backend.domain.User;
import com.kips.backend.repository.UserRepository;
import com.kips.backend.service.UserService;
import com.kips.backend.service.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public UserDto whoAmI() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userPrincipal = (User) authentication.getPrincipal();

        return UserDto.builder()
                .email(userPrincipal.getEmail())
                .firstName(userPrincipal.getFirstName())
                .lastName(userPrincipal.getLastName())
                .role(userPrincipal.getRole())
                .build();
    }
}
