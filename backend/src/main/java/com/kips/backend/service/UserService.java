package com.kips.backend.service;

import com.kips.backend.service.dto.UserDto;

public interface UserService {
    UserDto whoAmI();

    UserDto updateProfile(UserDto userDto);
}
