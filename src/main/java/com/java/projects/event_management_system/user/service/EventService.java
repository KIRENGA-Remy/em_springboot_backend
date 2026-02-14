package com.java.projects.event_management_system.user.service;

import com.java.projects.event_management_system.user.dto.request.CreateEventRequest;
import com.java.projects.event_management_system.user.entity.Event;
import com.java.projects.event_management_system.user.repository.EventRepository;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    private final EventRepository eventRepository;
    public EventService(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    public Event createEvent(CreateEventRequest request, Long organizerId){
        Event event = new Event(
                request.getTitle(),
                request.getDescription(),
                request.getTotalSeats(),
                organizerId,
                request.getEndTime(),
                request.getStartTime()
        );
        return eventRepository.save(event);
    }

    public Event publishEvent(Long eventId, Long organizerId){
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found!"));
        if(!event.getOrganizerId().equals(organizerId)){
            throw new RuntimeException("Not allowed");
        }
        event.publish();
        return event;
    }
}
