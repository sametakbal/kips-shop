package com.kips.backend.controller;


import com.kips.backend.service.UserService;
import com.kips.backend.service.dto.UserDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@Tag(name = "Users Controller")
@PreAuthorize("hasRole('ADMIN')")
public class UserController {
    private final UserService userService;

    @GetMapping("/whoami")
    public ResponseEntity<UserDto> whoAmI() {
        return ResponseEntity.ok(userService.whoAmI());
    }
}
