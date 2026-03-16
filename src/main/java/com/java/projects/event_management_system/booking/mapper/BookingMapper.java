package com.java.projects.event_management_system.booking.mapper;

import com.java.projects.event_management_system.booking.entity.Booking;
import com.java.projects.event_management_system.booking.request.CreateBookingRequest;
import com.java.projects.event_management_system.booking.response.BookingResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    BookingResponse toResponse(Booking booking);
}
