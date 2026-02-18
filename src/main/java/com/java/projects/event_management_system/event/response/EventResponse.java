package com.java.projects.event_management_system.event.response;

import com.java.projects.event_management_system.common.EventStatus;

import java.time.LocalDateTime;

public class EventResponse {
    private Long id;
    private String title;
    private String description;
    private int availableSeats;
    private EventStatus status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
