package com.java.projects.event_management_system.ticket.repository;

import com.java.projects.event_management_system.ticket.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    boolean existsByBookingId(Long bookingId);
}
