package com.technical_test.technical_test_java.controller;

import com.technical_test.technical_test_java.dto.UserRegistrationRequest;
import com.technical_test.technical_test_java.entity.User;
import com.technical_test.technical_test_java.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser_Success() {
        UserRegistrationRequest request = new UserRegistrationRequest();
        request.setEmail("test@example.com");
        request.setPassword("Password123");
        request.setName("Test User");

        User user = new User();
        user.setEmail(request.getEmail());
        user.setToken("jwtToken");

        when(userService.registerUser(any(UserRegistrationRequest.class))).thenReturn(user);

        ResponseEntity<?> result = userController.registerUser(request);

        assertNotNull(result);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals("test@example.com", ((User) result.getBody()).getEmail());
        assertEquals("jwtToken", ((User) result.getBody()).getToken());
    }

    @Test
    void testRegisterUser_EmailAlreadyExists() {
        UserRegistrationRequest request = new UserRegistrationRequest();
        request.setEmail("test@example.com");

        when(userService.registerUser(any(UserRegistrationRequest.class))).thenThrow(new RuntimeException("Email already registered"));

        ResponseEntity<?> result = userController.registerUser(request);

        assertNotNull(result);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals("Email already registered", result.getBody());
    }
}
