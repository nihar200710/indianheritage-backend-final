package com.sanchari.backend.repository;

import com.sanchari.backend.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    // This custom method allows the frontend to fetch
    // ONLY the bookings belonging to the logged-in user.
    List<Booking> findByUserIdOrderByIdDesc(Long userId);
}