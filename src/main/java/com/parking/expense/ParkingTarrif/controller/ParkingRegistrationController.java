package com.parking.expense.ParkingTarrif.controller;

import com.parking.expense.ParkingTarrif.entity.ParkingDuration;
import com.parking.expense.ParkingTarrif.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/parking")
public class ParkingRegistrationController {

    @Autowired
    private ParkingService parkingService;

    @PostMapping("/start")
    public ResponseEntity<ParkingDuration> startParking(@RequestParam String licensePlate, @RequestParam String streetName) {
        ParkingDuration duration = parkingService.startParking(licensePlate, streetName);
        return ResponseEntity.ok(duration);
    }

    @PostMapping("/end")
    public ResponseEntity<Double> endParking(@RequestParam String licensePlate) {
        double amount = parkingService.endParking(licensePlate);
        return ResponseEntity.ok(amount);
    }
}
