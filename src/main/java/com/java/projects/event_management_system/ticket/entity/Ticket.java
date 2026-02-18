package com.java.projects.event_management_system.ticket.entity;

import com.java.projects.event_management_system.booking.entity.Booking;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tickets",
      uniqueConstraints = {
        @UniqueConstraint(columnNames = "booking_id"),
        @UniqueConstraint(columnNames = "ticket_code")
      })
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @Column(name = "ticket_code", nullable = false)
    private String ticketCode;

    protected Ticket(){}

    public Ticket(Booking booking){
        this.booking = booking;
        this.ticketCode = UUID.randomUUID().toString();
    }
}
