package com.boxStudio.BoxStudio.service;

import com.boxStudio.BoxStudio.dto.BookingDTO;
import com.boxStudio.BoxStudio.model.*;
import com.boxStudio.BoxStudio.repositories.BookingRepository;
import com.boxStudio.BoxStudio.repositories.FightingAreaRepository;
import com.boxStudio.BoxStudio.repositories.TraineeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private TraineeRepository traineeRepository;
    @Autowired
    private FightingAreaRepository fightingAreaRepository;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public List<Booking> getBookingsByTrainee(Long traineeId) {
        return bookingRepository.findByTraineeId(traineeId);
    }

    public List<Booking> getBookingsByTraineeEmail(String email) {
        Trainee trainee = traineeRepository.findByEmail(email).orElse(null);
        if (trainee == null) return new ArrayList<>();
        return bookingRepository.findByTraineeId(trainee.getId());
    }

    public void createBooking(BookingDTO dto) {
        Trainee trainee = traineeRepository.findById(dto.getTraineeId()).orElse(null);
        if (trainee == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Trainee does not exist.");
        }

        LocalDateTime start = LocalDateTime.parse(dto.getStartTime());
        LocalDateTime end = LocalDateTime.parse(dto.getEndTime());

        List<FightingArea> areas = new ArrayList<>();
        for (Long areaId : dto.getFightingAreaIds()) {
            FightingArea area = fightingAreaRepository.findById(areaId).orElse(null);
            if (area == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fighting area does not exist.");
            }

            List<Booking> existingBookings = bookingRepository.findByFightingAreas_Id(areaId);

            long occupancy = 0;
            for (Booking b : existingBookings) {
                if (start.isBefore(b.getEndTime()) && end.isAfter(b.getStartTime())) {
                    occupancy++;
                }
            }

            if (occupancy >= area.getCapacity()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This area has reached its maximum capacity.");
            }

            areas.add(area);
        }

        Booking booking = new Booking(trainee, areas, start, end);
        bookingRepository.save(booking);
    }

    public void updateBooking(BookingDTO dto) {
        Booking booking = bookingRepository.findById(dto.getId()).orElse(null);
        if (booking == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Booking does not exist.");
        }

        LocalDateTime start = LocalDateTime.parse(dto.getStartTime());
        LocalDateTime end = LocalDateTime.parse(dto.getEndTime());

        List<FightingArea> neueAreas = new ArrayList<>();
        for (Long areaId : dto.getFightingAreaIds()) {
            FightingArea area = fightingAreaRepository.findById(areaId).orElse(null);
            if (area == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fighting area does not exist.");
            }

            List<Booking> existingBookings = bookingRepository.findByFightingAreas_Id(areaId);

            long occupancy = 0;
            for (Booking b : existingBookings) {
                if (!b.getId().equals(booking.getId())) {
                    if (start.isBefore(b.getEndTime()) && end.isAfter(b.getStartTime())) {
                        occupancy++;
                    }
                }
            }

            if (occupancy >= area.getCapacity()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This area has reached its maximum capacity.");
            }

            neueAreas.add(area);
        }

        booking.setStartTime(start);
        booking.setEndTime(end);
        booking.setFightingAreas(neueAreas);
        bookingRepository.save(booking);
    }

    public void deleteBooking(Long id) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Booking does not exist.");
        }

        if (LocalDateTime.now().isAfter(booking.getStartTime().minusHours(24))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot delete booking less than 24 hours before.");
        }

        bookingRepository.deleteById(id);
    }
}

