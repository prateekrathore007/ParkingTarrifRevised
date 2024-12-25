package com.parking.expense.ParkingTarrif.controller;

import com.parking.expense.ParkingTarrif.entity.ParkingDuration;
import com.parking.expense.ParkingTarrif.service.ParkingService;
import com.parking.expense.ParkingTarrif.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/parking")
public class ParkingRegistrationController {

    @Autowired
    private ParkingService parkingService;


    @Autowired
    private ProductService productService;

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


    @GetMapping("/loadDataFromCSV")
    public String getProductDetails() throws IOException {
        File csvFile=new ClassPathResource("Products.csv").getFile();
        productService.loadDataFromCsv(csvFile);
        return "SUCCESS";

    }
}
