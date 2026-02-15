package com.java.projects.event_management_system.user.controller;

import com.java.projects.event_management_system.user.dto.request.CreateBookingRequest;
import com.java.projects.event_management_system.user.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    @PostMapping
    public void createBooking(@Valid @RequestBody CreateBookingRequest request){
        Long userId = 1L;
        bookingService.createBooking(request, userId);
    }
}
