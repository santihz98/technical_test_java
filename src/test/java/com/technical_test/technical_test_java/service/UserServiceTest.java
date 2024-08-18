package com.technical_test.technical_test_java.service;

import com.technical_test.technical_test_java.dto.UserRegistrationRequest;
import com.technical_test.technical_test_java.entity.User;
import com.technical_test.technical_test_java.repository.UserRepository;
import com.technical_test.technical_test_java.security.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtService jwtService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

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

        User savedUser = new User();
        savedUser.setEmail(request.getEmail());
        savedUser.setPassword("encodedPassword");
        savedUser.setToken("jwtToken");

        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(jwtService.generateToken(anyString())).thenReturn("jwtToken");
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        User user = userService.registerUser(request);

        assertNotNull(user);
        assertEquals("encodedPassword", user.getPassword());
        assertEquals("jwtToken", user.getToken());
        assertEquals("test@example.com", user.getEmail());
    }

    @Test
    void testRegisterUser_EmailAlreadyExists() {
        UserRegistrationRequest request = new UserRegistrationRequest();
        request.setEmail("test@example.com");

        when(userRepository.existsByEmail(anyString())).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.registerUser(request));

        assertEquals("The email is already registered.", exception.getMessage());
    }
}
