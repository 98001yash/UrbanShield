package com.company.urban.UrbanShield.controller;

import com.company.urban.UrbanShield.advices.ApiError;
import com.company.urban.UrbanShield.advices.ApiResponse;
import com.company.urban.UrbanShield.dto.AuthResponse;
import com.company.urban.UrbanShield.dto.LoginDto;
import com.company.urban.UrbanShield.dto.SignupDto;
import com.company.urban.UrbanShield.dto.UserDto;
import com.company.urban.UrbanShield.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignupDto signupDto) {
        UserDto userDto = authService.signup(signupDto);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody LoginDto loginDto) {
        try {
            String token = authService.login(loginDto.getEmail(), loginDto.getPassword());
            AuthResponse authResponse = new AuthResponse(token);
            return ResponseEntity.ok(new ApiResponse<>(authResponse));
        } catch (RuntimeException e) {

            ApiError error = ApiError.builder()
                    .status(HttpStatus.UNAUTHORIZED)
                    .message("Invalid credentials")
                    .subErrors(Collections.singletonList(e.getMessage()))
                    .build();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>(error));
        }
    }

}
