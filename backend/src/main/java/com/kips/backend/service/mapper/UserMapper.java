package com.kips.backend.service.mapper;

import com.kips.backend.domain.User;
import com.kips.backend.service.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public UserDto toDto(User user) {
        if (user == null) {
            return null;
        }
        return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    public User userFromId(Integer userId) {
        if (userId == null) {
            return null;
        }
        return User.builder().id(userId).build();
    }
}
