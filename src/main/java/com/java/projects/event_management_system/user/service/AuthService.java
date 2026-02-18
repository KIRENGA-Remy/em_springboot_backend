package com.java.projects.event_management_system.user.service;

import com.java.projects.event_management_system.security.jwt.JwtService;
import com.java.projects.event_management_system.user.dto.request.RegisterRequest;
import com.java.projects.event_management_system.user.dto.request.LoginRequest;
import com.java.projects.event_management_system.user.entity.User;
import com.java.projects.event_management_system.user.userDetails.CustomUserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import com.java.projects.event_management_system.user.repository.UserRepository;
import com.java.projects.event_management_system.user.dto.response.AuthResponse;


@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public AuthResponse register(RegisterRequest request) {

        User user = new User(
                request.email(),
                passwordEncoder.encode(request.password()),
                request.role()
        );

        userRepository.save(user);

        String token = jwtService.generateToken(
                new CustomUserDetails(user));

        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        User user = userRepository
                .findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtService.generateToken(
                new CustomUserDetails(user));

        return new AuthResponse(token);
    }
}
