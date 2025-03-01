package com.company.urban.UrbanShield.services;

import com.company.urban.UrbanShield.dto.UserDto;
import com.company.urban.UrbanShield.entities.User;
import com.company.urban.UrbanShield.enums.UserRole;
import com.company.urban.UrbanShield.exceptions.ResourceNotFoundException;
import com.company.urban.UrbanShield.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class  UserService {

    private final UserRepository userRepository;

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: "+id));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
    }
}
