package com.boxStudio.BoxStudio.repositories;

import com.boxStudio.BoxStudio.model.FightingArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FightingAreaRepository extends JpaRepository<FightingArea, Long> {
}

