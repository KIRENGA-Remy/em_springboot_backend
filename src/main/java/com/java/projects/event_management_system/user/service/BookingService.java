package com.java.projects.event_management_system.user.service;

import com.java.projects.event_management_system.common.EventStatus;
import com.java.projects.event_management_system.user.dto.request.CreateBookingRequest;
import com.java.projects.event_management_system.user.entity.Booking;
import com.java.projects.event_management_system.user.entity.Event;
import com.java.projects.event_management_system.user.repository.BookingRepository;
import com.java.projects.event_management_system.user.repository.EventRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final EventRepository eventRepository;
    public BookingService(BookingRepository bookingRepository, EventRepository eventRepository){
        this.bookingRepository = bookingRepository;
        this.eventRepository = eventRepository;
    }

    @Transactional
    public Booking createBooking(CreateBookingRequest request, Long userId){
        Event event = eventRepository.findByIdAndUpdate(request.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found"));

        if(event.getStatus() != EventStatus.PUBLISHED){
            throw new RuntimeException("Event not open for booking");
        }

        if (event.getAvailableSeats() <= 0){
            throw new RuntimeException("No seats available");
        }

        event.decreaseAvailableSeats();

        Booking booking = new Booking(event, userId);
        return bookingRepository.save(booking);
    }
}
