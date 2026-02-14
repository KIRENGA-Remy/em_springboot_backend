package com.java.projects.event_management_system.user.mapper;

import com.java.projects.event_management_system.user.dto.request.CreateEventRequest;
import com.java.projects.event_management_system.user.dto.response.EventResponse;
import com.java.projects.event_management_system.user.entity.Event;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {
    Event toEntity(CreateEventRequest request, Long organizerId);
    EventResponse toResponse(Event event);
}
