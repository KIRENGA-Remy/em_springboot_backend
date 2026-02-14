package com.java.projects.event_management_system.user.controller;

import com.java.projects.event_management_system.user.dto.request.CreateEventRequest;
import com.java.projects.event_management_system.user.dto.response.EventResponse;
import com.java.projects.event_management_system.user.mapper.EventMapper;
import com.java.projects.event_management_system.user.service.EventService;
import jakarta.validation.Valid;
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
