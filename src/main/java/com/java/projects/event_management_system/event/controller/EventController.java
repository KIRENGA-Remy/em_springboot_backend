package com.java.projects.event_management_system.event.controller;

import com.java.projects.event_management_system.event.request.CreateEventRequest;
import com.java.projects.event_management_system.event.response.EventResponse;
import com.java.projects.event_management_system.event.mapper.EventMapper;
import com.java.projects.event_management_system.event.service.EventService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;
    private final EventMapper eventMapper;

    public EventController(EventService eventService, EventMapper eventMapper){
        this.eventService = eventService;
        this.eventMapper = eventMapper;
    }

    @PreAuthorize("hasRole('ORGANIZER')")
    @PostMapping()
    public EventResponse create(@Valid @RequestBody CreateEventRequest request){
        Long organizerId = 1L;
        return eventMapper.toResponse(
                eventService.createEvent(request, organizerId)
        );
    }

    @PostMapping("/{id}/publish")
    public EventResponse publish(@PathVariable Long id){
        Long organizerId = 1L;
        return eventMapper.toResponse(
                eventService.publishEvent(id, organizerId)
        );
    }
}
