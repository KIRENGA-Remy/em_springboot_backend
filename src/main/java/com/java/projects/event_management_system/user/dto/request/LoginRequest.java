package com.java.projects.event_management_system.user.dto.request;

public record LoginRequest(
        String email,
        String password
) {}
