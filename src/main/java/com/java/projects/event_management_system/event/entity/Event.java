package com.java.projects.event_management_system.event.entity;

import com.java.projects.event_management_system.common.EventStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(name = "organizer_id", nullable = false)
    private Long organizerId;

    @Column(name = "total_seats", nullable = false)
    private int totalSeats;

    @Column(name = "available_seats", nullable = false)
    private int availableSeats;

    @Enumerated(EnumType.STRING)
    @Column( nullable = false)
    private EventStatus status;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public EventStatus getStatus() {
        return status;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public Long getOrganizerId() {
        return organizerId;
    }

    protected Event() {}

    public Event(String title, String description, int totalSeats,
                 Long organizerId, LocalDateTime startTime, LocalDateTime endTime){
        this.title = title;
        this.description = description;
        this.organizerId = organizerId;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
        this.status = EventStatus.DRAFT;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void publish(){
        if(this.status == EventStatus.PUBLISHED){
            throw new IllegalStateException("Event already published");
        }
        this.status = EventStatus.PUBLISHED;
    }

    public void decreaseAvailableSeats(){
        if(this.availableSeats <= 0){
            throw new IllegalStateException("No seat left");
        }
        this.availableSeats--;
    }
}
