package com.boxStudio.BoxStudio.controller;

import com.boxStudio.BoxStudio.dto.BookingDTO;
import com.boxStudio.BoxStudio.model.Booking;
import com.boxStudio.BoxStudio.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/bookings")
    public ResponseEntity getAllBookings(Authentication authentication) {
        Logger.getLogger("BookingController").info("Getting Bookings");

        boolean isManager = false;
        for (GrantedAuthority auth : authentication.getAuthorities()) {
            if (auth.getAuthority().equals("ROLE_MANAGER")) {
                isManager = true;
                break;
            }
        }

        if (isManager) {
            return ResponseEntity.status(HttpStatus.OK).body(bookingService.getAllBookings());
        }

        List<Booking> bookings = bookingService.getBookingsByTraineeEmail(authentication.getName());
        return ResponseEntity.status(HttpStatus.OK).body(bookings);
    }

    @PostMapping("/bookings")
    public ResponseEntity createBooking(@RequestBody BookingDTO bookingDTO) {
        Logger.getLogger("BookingController").info("Creating a new Booking");
        bookingService.createBooking(bookingDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Booking created successfully.");
    }

    @PutMapping("/bookings")
    public ResponseEntity updateBooking(@RequestBody BookingDTO bookingDTO) {
        Logger.getLogger("BookingController").info("Updating a Booking");
        bookingService.updateBooking(bookingDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Booking updated successfully.");
    }

    @DeleteMapping("/bookings/delete/{id}")
    public ResponseEntity deleteBooking(@PathVariable Long id) {
        Logger.getLogger("BookingController").info("Deleting a Booking");
        bookingService.deleteBooking(id);
        return ResponseEntity.status(HttpStatus.OK).body("Booking deleted successfully.");
    }
}


