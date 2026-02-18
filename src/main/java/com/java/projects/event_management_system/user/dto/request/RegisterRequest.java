package com.java.projects.event_management_system.user.dto.request;

import com.java.projects.event_management_system.common.UserRole;

public record RegisterRequest(
        String email,
        String password,
        UserRole role
) {}

