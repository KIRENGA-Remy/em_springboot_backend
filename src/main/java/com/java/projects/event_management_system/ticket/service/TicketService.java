package com.java.projects.event_management_system.ticket.service;

import com.java.projects.event_management_system.booking.entity.Booking;
import com.java.projects.event_management_system.ticket.entity.Ticket;
import com.java.projects.event_management_system.ticket.repository.TicketRepository;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }

    public void generateTicket(Booking booking){
        boolean exists = ticketRepository.existsByBookingId(booking.getId());
        if (exists) return;

        Ticket ticket = new Ticket(booking);
        ticketRepository.save(ticket);
    }
}
