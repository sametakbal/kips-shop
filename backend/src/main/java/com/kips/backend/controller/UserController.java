package com.kips.backend.controller;


import com.kips.backend.service.UserService;
import com.kips.backend.service.dto.UserDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@Tag(name = "Users Controller")
@PreAuthorize("hasAnyRole('ADMIN','USER','MANAGER')")
public class UserController {
    private final UserService userService;

    @GetMapping("/who-am-i")
    public ResponseEntity<UserDto> whoAmI() {
        return ResponseEntity.ok(userService.whoAmI());
    }

    @PostMapping("/update-profile")
    public ResponseEntity<UserDto> updateProfile(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.updateProfile(userDto));
    }

}
