package com.parking.expense.ParkingTarrif.service;

import com.parking.expense.ParkingTarrif.entity.Observation;
import com.parking.expense.ParkingTarrif.repository.ObservationRepository;
import com.parking.expense.ParkingTarrif.repository.ParkingDurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReportService {

    @Autowired
    private ObservationRepository observationRepository;

    @Autowired
    private ParkingDurationRepository parkingDurationRepository;

    @Scheduled(cron = "0 0 1 * * *")
    public void generateReport() {
        List<Observation> observations = observationRepository.findAll();
        observations.stream()
                .filter(observation -> !parkingDurationRepository.findByLicensePlateAndEndTimeIsNull(observation.getLicensePlate()).isPresent())
                .forEach(observation -> {
                    // Generate a report or take necessary action
                    System.out.println("Unregistered plate: " + observation.getLicensePlate() +
                            " observed at " + observation.getStreetName() +
                            " on " + observation.getDateOfObservation());
                });
    }
}
