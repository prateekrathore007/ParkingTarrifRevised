package com.parking.expense.ParkingTarrif.service;

import com.parking.expense.ParkingTarrif.entity.ParkingDuration;
import com.parking.expense.ParkingTarrif.exception.ParkingDurationNotFoundException;
import com.parking.expense.ParkingTarrif.repository.ParkingDurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;
import java.util.Optional;

@Service
public class ParkingService {
    @Autowired
    private ParkingDurationRepository parkingDurationRepository;

    private final Map<String, Integer> streetPricing = Map.of(
            "Java", 15,
            "Jakarta", 13,
            "Spring", 10,
            "Azure", 10
    );

    public ParkingDuration startParking(String licensePlate, String streetName) {
        ParkingDuration duration= new ParkingDuration();
        duration.setLicensePlate(licensePlate);
        duration.setStreetName(streetName);
        duration.setStartTime(LocalDateTime.now());
        return parkingDurationRepository.save(duration);

    }

    public double endParking(String licensePlate) {
        Optional<ParkingDuration> durationOpt = parkingDurationRepository.findByLicensePlateAndEndTimeIsNull(licensePlate);
        if (durationOpt.isEmpty()) {
            throw new ParkingDurationNotFoundException("Parking session not found for license plate: " + licensePlate);
        }

        ParkingDuration duration = durationOpt.get();
        duration.setEndTime(LocalDateTime.now());
        parkingDurationRepository.save(duration);

        long parkingTime = calculateChargedMinutes(duration.getStartTime(), duration.getEndTime());

        //getting parking pricing of Respective Streets
        int pricePerMinute = streetPricing.get(duration.getStreetName());

        return parkingTime * pricePerMinute / 100.0;
    }


    public long calculateChargedMinutes(LocalDateTime start, LocalDateTime end) {
        long totalChargedMinutes = 0;

        LocalDateTime current = start;
        while (current.isBefore(end)) {
            LocalDateTime next = current.plusMinutes(1);

            if (isChargedMinute(current)) {
                totalChargedMinutes++;
            }

            current = next;
        }

        return totalChargedMinutes;
    }

    public boolean isChargedMinute(LocalDateTime time) {
        DayOfWeek day = time.getDayOfWeek();
        LocalTime localTime = time.toLocalTime();

        if (day == DayOfWeek.SUNDAY) {
            return false;
        }

        if (localTime.isAfter(LocalTime.of(21, 0)) || localTime.isBefore(LocalTime.of(8, 0))) {
            return false;
        }

        return true;
    }
}

