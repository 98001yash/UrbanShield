package com.company.urban.UrbanShield.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginResponseDto {
    private String token;
    private UserDto user;
}
