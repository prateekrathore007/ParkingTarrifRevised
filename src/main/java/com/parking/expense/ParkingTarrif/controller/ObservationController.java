package com.parking.expense.ParkingTarrif.controller;


import com.parking.expense.ParkingTarrif.entity.Observation;
import com.parking.expense.ParkingTarrif.repository.ObservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/list")
public class ObservationController {

    @Autowired
    private ObservationRepository observationRepository;

    @PostMapping("/observations")
    public ResponseEntity<List<Observation>> addObservations(@RequestBody List<Observation> observations) {
        List<Observation> savedObservations = observationRepository.saveAll(observations);
        return ResponseEntity.ok(savedObservations);
    }
}
