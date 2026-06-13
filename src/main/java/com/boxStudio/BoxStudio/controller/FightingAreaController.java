package com.boxStudio.BoxStudio.controller;

import com.boxStudio.BoxStudio.dto.FightingAreaDTO;
import com.boxStudio.BoxStudio.model.FightingArea;
import com.boxStudio.BoxStudio.service.FightingAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class FightingAreaController {

    @Autowired
    private FightingAreaService fightingAreaService;

    @PostMapping("/areas")
    public ResponseEntity createArea(@RequestBody FightingAreaDTO areaDTO) {
        Logger.getLogger("FightingAreaController").info("Creating a new Fighting Area");
        fightingAreaService.createArea(areaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Fighting area created successfully.");
    }

    @GetMapping("/areas")
    public ResponseEntity<List<FightingArea>> getAllAreas() {
        Logger.getLogger("FightingAreaController").info("Getting all Fighting Areas");
        List<FightingArea> areas = fightingAreaService.getAllAreas();
        return ResponseEntity.status(HttpStatus.OK).body(areas);
    }

    @DeleteMapping("/areas/delete/{id}")
    public ResponseEntity deleteArea(@PathVariable Long id) {
        Logger.getLogger("FightingAreaController").info("Deleting a Fighting Area");
        fightingAreaService.deleteArea(id);
        return ResponseEntity.status(HttpStatus.OK).body("Fighting area successfully deleted.");
    }
}

