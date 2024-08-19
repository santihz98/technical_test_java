package com.technical_test.technical_test_java.service;

import com.technical_test.technical_test_java.dto.UserRegistrationRequest;
import com.technical_test.technical_test_java.entity.User;
import com.technical_test.technical_test_java.repository.UserRepository;
import com.technical_test.technical_test_java.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public User registerUser(UserRegistrationRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El correo ya esta registrado");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setCreated(new Date());
        user.setModified(new Date());
        user.setLastLogin(new Date());
        user.setIsActive(true);
        user.setToken(jwtService.generateToken(user.getEmail()));
        user.setPhones(request.getPhones());

        return userRepository.save(user);
    }
}
