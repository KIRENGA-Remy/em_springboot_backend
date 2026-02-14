package com.java.projects.event_management_system.user.dto.request;

import jakarta.validation.constraints.NotNull;

public class CreateBookingRequest {

    @NotNull
    private Long eventId;
}
