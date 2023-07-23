package com.kips.backend.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kips.backend.common.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
}
