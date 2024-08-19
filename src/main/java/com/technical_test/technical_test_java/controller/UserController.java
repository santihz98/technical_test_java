package com.technical_test.technical_test_java.controller;

import com.technical_test.technical_test_java.service.UserService;
import com.technical_test.technical_test_java.dto.UserRegistrationRequest;
import com.technical_test.technical_test_java.exception.UserAlreadyExistsException;
import com.technical_test.technical_test_java.entity.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationRequest request) {
        try {
            User user = userService.registerUser(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (UserAlreadyExistsException e) {
            throw new UserAlreadyExistsException("El correo ya esta registrado");
        }
    }
}
