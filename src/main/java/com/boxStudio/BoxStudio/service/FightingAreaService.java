package com.boxStudio.BoxStudio.service;

import com.boxStudio.BoxStudio.dto.FightingAreaDTO;
import com.boxStudio.BoxStudio.model.FightingArea;
import com.boxStudio.BoxStudio.repositories.FightingAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class FightingAreaService {

    @Autowired
    private FightingAreaRepository fightingAreaRepository;

    public void createArea(FightingAreaDTO dto) {
        if (dto.getName() == null || dto.getName().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid area name.");
        }

        FightingArea area = new FightingArea(dto.getName(), dto.getCapacity());
        fightingAreaRepository.save(area);
    }

    public List<FightingArea> getAllAreas() {
        return fightingAreaRepository.findAll();
    }

    public void deleteArea(Long id) {
        if (!fightingAreaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fighting area does not exist.");
        }
        fightingAreaRepository.deleteById(id);
    }
}

