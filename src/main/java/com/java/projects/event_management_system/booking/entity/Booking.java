package com.java.projects.event_management_system.booking.entity;

import com.java.projects.event_management_system.common.BookingStatus;
import com.java.projects.event_management_system.event.entity.Event;
import com.java.projects.event_management_system.user.entity.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    protected Booking(){}

    public Booking(Event event, User user){
        this.event = event;
        this.user = user;
        this.status = BookingStatus.PENDING;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public Event getEvent() {
        return event;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void confirm(){
        this.status = BookingStatus.CONFIRMED;
    }

    public void cancel(){
        this.status = BookingStatus.CANCELLED;
    }
}
