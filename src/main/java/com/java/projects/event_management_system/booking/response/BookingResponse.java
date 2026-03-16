package com.java.projects.event_management_system.booking.response;

import com.java.projects.event_management_system.common.BookingStatus;
import com.java.projects.event_management_system.event.entity.Event;
import com.java.projects.event_management_system.user.entity.User;

import java.time.LocalDateTime;

public class BookingResponse {
    private Long id;
    private User user;
    private Event event;
    private BookingStatus status;
    private LocalDateTime createdAt;
}
