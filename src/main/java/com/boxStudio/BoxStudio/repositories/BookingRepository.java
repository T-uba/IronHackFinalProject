package com.boxStudio.BoxStudio.repositories;

import com.boxStudio.BoxStudio.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByTraineeId(Long traineeId);
    List<Booking> findByFightingAreas_Id(Long areaId);
}

