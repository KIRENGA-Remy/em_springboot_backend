package com.java.projects.event_management_system.booking.request;

import jakarta.validation.constraints.NotNull;

public class CreateBookingRequest {

    @NotNull
    private Long eventId;

    public Long getEventId() {
        return eventId;
    }
}
