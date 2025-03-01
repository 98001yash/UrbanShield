package com.company.urban.UrbanShield.dto;

import com.company.urban.UrbanShield.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String email;

    private UserRole role;
}