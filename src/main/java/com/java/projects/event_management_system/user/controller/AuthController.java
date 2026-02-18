package com.java.projects.event_management_system.user.controller;

import com.java.projects.event_management_system.user.dto.request.LoginRequest;
import com.java.projects.event_management_system.user.dto.request.RegisterRequest;
import com.java.projects.event_management_system.user.dto.response.AuthResponse;
import com.java.projects.event_management_system.user.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public AuthResponse register(
            @RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }

    @PostMapping("/login")
    public AuthResponse login(
            @RequestBody LoginRequest request) {
        return authService.login(request);
    }
}

