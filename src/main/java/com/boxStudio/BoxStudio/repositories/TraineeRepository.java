package com.boxStudio.BoxStudio.repositories;

import com.boxStudio.BoxStudio.model.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee, Long> {

    boolean existsByEmail(String email);
    Optional<Trainee> findByEmail(String email);
}


