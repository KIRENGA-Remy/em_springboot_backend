package com.java.projects.event_management_system.booking.controller;

import com.java.projects.event_management_system.booking.request.CreateBookingRequest;
import com.java.projects.event_management_system.booking.service.BookingService;
import com.java.projects.event_management_system.user.userDetails.CustomUserDetails;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public void createBooking(
            @Valid @RequestBody CreateBookingRequest request,
            @AuthenticationPrincipal CustomUserDetails userDetails
            ){
        bookingService.createBooking(request, userDetails.getUser());
    }
}
