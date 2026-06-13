package com.boxStudio.BoxStudio.controller;

import com.boxStudio.BoxStudio.dto.TraineesDTO;
import com.boxStudio.BoxStudio.model.Trainee;
import com.boxStudio.BoxStudio.service.TraineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class TraineeController {

    @Autowired
    private TraineeService traineeService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody TraineesDTO traineesDTO) {
        Logger.getLogger("TraineeController").info("Registering a new Trainee");
        traineeService.createTrainee(traineesDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Trainee registered successfully.");
    }

    @PostMapping("/register/manager")
    public ResponseEntity registerManager(@RequestBody TraineesDTO traineesDTO) {
        Logger.getLogger("TraineeController").info("Registering a new Manager");
        traineeService.createManager(traineesDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Manager registered successfully.");
    }

    @GetMapping("/trainees")
    public ResponseEntity getAllTrainees(Authentication authentication) {
        Logger.getLogger("TraineeController").info("Getting all Trainees");

        boolean isManager = false;
        for (GrantedAuthority auth : authentication.getAuthorities()) {
            if (auth.getAuthority().equals("ROLE_MANAGER")) {
                isManager = true;
                break;
            }
        }

        if (!isManager) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied. Managers only.");
        }

        List<TraineesDTO> dtos = new ArrayList<>();
        for (Trainee t : traineeService.getAllTrainees()) {
            TraineesDTO dto = new TraineesDTO();
            dto.setFirstName(t.getFirstName());
            dto.setLastName(t.getLastName());
            dto.setEmail(t.getEmail());
            dtos.add(dto);
        }

        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    @DeleteMapping("/trainees/delete/{id}")
    public ResponseEntity deleteAccount(@PathVariable Long id) {
        Logger.getLogger("TraineeController").info("Deleting a Trainee Account");
        traineeService.deleteTrainee(id);
        return ResponseEntity.status(HttpStatus.OK).body("Account deleted successfully.");
    }
}

