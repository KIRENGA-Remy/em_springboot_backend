package com.java.projects.event_management_system.booking.service;

import com.java.projects.event_management_system.common.EventStatus;
import com.java.projects.event_management_system.booking.request.CreateBookingRequest;
import com.java.projects.event_management_system.booking.entity.Booking;
import com.java.projects.event_management_system.event.entity.Event;
import com.java.projects.event_management_system.booking.repository.BookingRepository;
import com.java.projects.event_management_system.event.repository.EventRepository;
import com.java.projects.event_management_system.user.entity.User;
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
    public Booking createBooking(CreateBookingRequest request, User user){
        Event event = eventRepository.findByIdAndUpdate(request.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found"));

        if(event.getStatus() != EventStatus.PUBLISHED){
            throw new RuntimeException("Event not open for booking");
        }

        if (event.getAvailableSeats() <= 0){
            throw new RuntimeException("No seats available");
        }

        event.decreaseAvailableSeats();

        Booking booking = new Booking(event, user);

        return bookingRepository.save(booking);
    }
}
