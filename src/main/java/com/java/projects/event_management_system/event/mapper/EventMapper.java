package com.java.projects.event_management_system.event.mapper;

import com.java.projects.event_management_system.event.request.CreateEventRequest;
import com.java.projects.event_management_system.event.response.EventResponse;
import com.java.projects.event_management_system.event.entity.Event;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {
    Event toEntity(CreateEventRequest request, Long organizerId);
    EventResponse toResponse(Event event);
}
