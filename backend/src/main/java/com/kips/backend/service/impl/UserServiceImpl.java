package com.kips.backend.service.impl;

import com.kips.backend.common.exception.GeneralException;
import com.kips.backend.domain.User;
import com.kips.backend.repository.UserRepository;
import com.kips.backend.service.UserService;
import com.kips.backend.service.dto.UserDto;
import com.kips.backend.service.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public UserDto whoAmI() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userPrincipal = (User) authentication.getPrincipal();
        return userMapper.toDto(userPrincipal);
    }

    @Override
    public UserDto updateProfile(UserDto userDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userPrincipal = (User) authentication.getPrincipal();
        Optional<User> userOptional = userRepository.findByEmail(userPrincipal.getEmail());
        if (userOptional.isEmpty()) {
            throw new GeneralException("User not found");
        }
        User user = userOptional.get();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        if (!user.getEmail().equals(userDto.getEmail())) {
            user.setEnabled(false);
            user.setEmail(userDto.getEmail());
        }
        User saved = userRepository.save(user);
        return userMapper.toDto(saved);
    }
}
