package com.java.projects.event_management_system.user.service;

import com.java.projects.event_management_system.user.repository.BookingRepository;
import com.java.projects.event_management_system.user.repository.EventRepository;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final EventRepository eventRepository;
    public BookingService(BookingRepository bookingRepository, EventRepository eventRepository){
        this.bookingRepository = bookingRepository;
        this.eventRepository = eventRepository;
    }
}
