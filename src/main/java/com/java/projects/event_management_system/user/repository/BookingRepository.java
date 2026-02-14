package com.java.projects.event_management_system.user.repository;

import com.java.projects.event_management_system.user.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}
