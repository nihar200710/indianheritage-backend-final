package com.sanchari.backend.controller;

import com.sanchari.backend.model.Booking;
import com.sanchari.backend.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:5173") // Allows your React app to connect
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    // Get bookings ONLY for the logged-in user
    @GetMapping("/user/{userId}")
    public List<Booking> getMyBookings(@PathVariable Long userId) {
        return bookingRepository.findByUserIdOrderByIdDesc(userId);
    }

    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {
        // Ensure status is PENDING for new entries as requested
        if (booking.getStatus() == null)
            booking.setStatus("PENDING");
        return bookingRepository.save(booking);
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Booking> cancelBooking(@PathVariable Long id) {
        return bookingRepository.findById(id)
                .map(booking -> {
                    booking.setStatus("Cancelled");
                    return ResponseEntity.ok(bookingRepository.save(booking));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}