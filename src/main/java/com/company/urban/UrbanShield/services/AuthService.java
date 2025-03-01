package com.company.urban.UrbanShield.services;

import com.company.urban.UrbanShield.dto.SignupDto;
import com.company.urban.UrbanShield.dto.UserDto;

public interface AuthService {

    String  login(String email , String password);

    UserDto signup(SignupDto signupDto);
}
