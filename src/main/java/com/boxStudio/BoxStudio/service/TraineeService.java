package com.boxStudio.BoxStudio.service;

import com.boxStudio.BoxStudio.dto.TraineesDTO;
import com.boxStudio.BoxStudio.model.Trainee;
import com.boxStudio.BoxStudio.repositories.TraineeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TraineeService {

    @Autowired
    private TraineeRepository traineeRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void createTrainee(TraineesDTO traineesDTO) {
        if (traineesDTO.getFirstName() == null || traineesDTO.getFirstName().trim().length() < 2) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The provided name is too short.");
        }
        if (traineeRepository.existsByEmail(traineesDTO.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is already taken.");
        }

        Trainee trainee = new Trainee(
                traineesDTO.getFirstName(),
                traineesDTO.getLastName(),
                traineesDTO.getEmail(),
                passwordEncoder.encode(traineesDTO.getPassword()),
                "ROLE_TRAINEE"
        );
        traineeRepository.save(trainee);
    }

    public void createManager(TraineesDTO traineesDTO) {
        if (traineesDTO.getFirstName() == null || traineesDTO.getFirstName().trim().length() < 2) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The provided name is too short.");
        }
        if (traineeRepository.existsByEmail(traineesDTO.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is already taken.");
        }

        Trainee manager = new Trainee(
                traineesDTO.getFirstName(),
                traineesDTO.getLastName(),
                traineesDTO.getEmail(),
                passwordEncoder.encode(traineesDTO.getPassword()),
                "ROLE_MANAGER"
        );
        traineeRepository.save(manager);
    }

    public void deleteTrainee(Long id) {
        if (!traineeRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Trainee account does not exist.");
        }
        traineeRepository.deleteById(id);
    }

    public List<Trainee> getAllTrainees() {
        return traineeRepository.findAll();
    }
}


