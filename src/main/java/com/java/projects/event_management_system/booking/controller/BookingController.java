package com.java.projects.event_management_system.booking.controller;

import com.java.projects.event_management_system.booking.mapper.BookingMapper;
import com.java.projects.event_management_system.booking.request.CreateBookingRequest;
import com.java.projects.event_management_system.booking.response.BookingResponse;
import com.java.projects.event_management_system.booking.service.BookingService;
import com.java.projects.event_management_system.user.userDetails.CustomUserDetails;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final BookingMapper bookingMapper;

    public BookingController(BookingService bookingService, BookingMapper bookingMapper){
        this.bookingService = bookingService;
        this.bookingMapper = bookingMapper;
    }

    @PostMapping
    public void createBooking(
            @Valid @RequestBody CreateBookingRequest request,
            @AuthenticationPrincipal CustomUserDetails userDetails
            ){
        bookingService.createBooking(request, userDetails.getUser());
    }

    @GetMapping("/{id}")
    public BookingResponse getBooking(@PathVariable Long id){
        return bookingMapper.toResponse(
                bookingService.getBooking(id)
        );
    }
}
