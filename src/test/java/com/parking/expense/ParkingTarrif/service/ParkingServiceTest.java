package com.parking.expense.ParkingTarrif.service;



import com.parking.expense.ParkingTarrif.entity.ParkingDuration;
import com.parking.expense.ParkingTarrif.exception.ParkingDurationNotFoundException;
import com.parking.expense.ParkingTarrif.repository.ParkingDurationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class ParkingServiceTest {

    @InjectMocks
    private ParkingService parkingService;

    @Mock
    private ParkingDurationRepository parkingDurationRepository;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void testStartParking() {
        ParkingDuration duration = new ParkingDuration();
        duration.setLicensePlate("ABC123");
        duration.setStreetName("Java");
        duration.setStartTime(LocalDateTime.now());

        when(parkingDurationRepository.save(any(ParkingDuration.class))).thenReturn(duration);

        ParkingDuration createdSession = parkingService.startParking("ABC123", "Java");

        assertNotNull(createdSession);
        assertEquals("ABC123", createdSession.getLicensePlate());
        assertEquals("Java", createdSession.getStreetName());
    }

    @Test
    void testEndParking() {
        ParkingDuration duration = new ParkingDuration();
        duration.setLicensePlate("ABC123");
        duration.setStreetName("Java");
        duration.setStartTime(LocalDateTime.of(2024, 7, 18, 14, 0));
        duration.setEndTime(null);

        when(parkingDurationRepository.findByLicensePlateAndEndTimeIsNull("ABC123")).thenReturn(Optional.of(duration));
        when(parkingDurationRepository.save(any(ParkingDuration.class))).thenReturn(duration);

        double amount = parkingService.endParking("ABC123");

        assertTrue(amount > 0);
        assertNotNull(duration.getEndTime());
    }

    @Test
    void testEndParking_NotFound() {
        when(parkingDurationRepository.findByLicensePlateAndEndTimeIsNull("ABC123")).thenReturn(Optional.empty());

        assertThrows(ParkingDurationNotFoundException.class, () -> parkingService.endParking("ABC123"));
    }

    @Test
    void testCalculateChargedMinutes() {
        LocalDateTime start = LocalDateTime.of(2024, 7, 18, 14, 0);
        LocalDateTime end = LocalDateTime.of(2024, 7, 18, 15, 0);

        long chargedMinutes = parkingService.calculateChargedMinutes(start, end);

        assertEquals(60, chargedMinutes);
    }

    @Test
    void testCalculateChargedMinutesWithFreePeriods() {
        LocalDateTime start = LocalDateTime.of(2024, 7, 18, 20, 50); // Start 10 minutes before free period
        LocalDateTime end = LocalDateTime.of(2024, 7, 19, 8, 10); // End 10 minutes after free period

        long chargedMinutes = parkingService.calculateChargedMinutes(start, end);

        assertEquals(21, chargedMinutes);
    }
}
