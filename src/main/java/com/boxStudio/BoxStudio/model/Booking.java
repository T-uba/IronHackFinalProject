package com.boxStudio.BoxStudio.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trainee_id")
    private Trainee trainee;

    @ManyToMany
    @JoinTable(
            name = "booking_fighting_area",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "fighting_area_id")
    )
    private List<FightingArea> fightingAreas;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Booking() {}
    public Booking(Trainee trainee, List<FightingArea> fightingAreas, LocalDateTime startTime, LocalDateTime endTime) {
        this.trainee = trainee;
        this.fightingAreas = fightingAreas;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Trainee getTrainee() {
        return trainee;
    }

    public void setTrainee(Trainee trainee) {
        this.trainee = trainee;
    }

    public List<FightingArea> getFightingAreas() {
        return fightingAreas;
    }

    public void setFightingAreas(List<FightingArea> fightingAreas) {
        this.fightingAreas = fightingAreas;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", traineeId=" + (trainee != null ? trainee.getId() : null) +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}


