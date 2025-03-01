package com.company.urban.UrbanShield.services.Impl;

import com.company.urban.UrbanShield.dto.SignupDto;
import com.company.urban.UrbanShield.dto.UserDto;
import com.company.urban.UrbanShield.entities.User;
import com.company.urban.UrbanShield.enums.UserRole;
import com.company.urban.UrbanShield.repositories.UserRepository;
import com.company.urban.UrbanShield.security.JwtService;
import com.company.urban.UrbanShield.services.AuthService;
import com.company.urban.UrbanShield.services.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;
    private final UserService userService;


    @Override
    public String login(String email, String password) {
        User user = userService.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            // Generate a JWT token
            return jwtService.generateAccessToken(user);
        } else {
            throw new RuntimeException("Invalid email or password");
        }
    }


    @Override
    public UserDto signup(SignupDto signupDto) {
        User user = modelMapper.map(signupDto, User.class);
        user.setPassword(passwordEncoder.encode(signupDto.getPassword()));
        user.setRole(UserRole.VIEWER);
        user = userRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }
}
