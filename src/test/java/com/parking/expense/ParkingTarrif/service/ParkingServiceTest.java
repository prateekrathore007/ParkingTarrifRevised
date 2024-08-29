package com.parking.expense.ParkingTarrif.service;



import com.parking.expense.ParkingTarrif.entity.ParkingDuration;
import com.parking.expense.ParkingTarrif.entity.StreetPricing;
import com.parking.expense.ParkingTarrif.exception.ParkingDurationNotFoundException;
import com.parking.expense.ParkingTarrif.repository.ParkingDurationRepository;
import com.parking.expense.ParkingTarrif.repository.StreetPricingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

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

    @Mock
    private StreetPricingRepository streetPricingRepository;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void testStartParking() {
        ParkingDuration duration = new ParkingDuration();
        duration.setLicensePlate("AA-ZK-BL");
        duration.setStreetName("Java");
        duration.setStartTime(LocalDateTime.now());

        when(parkingDurationRepository.save(any(ParkingDuration.class))).thenReturn(duration);

        ParkingDuration createdSession = parkingService.startParking("AA-ZK-BL", "Java");

        assertNotNull(createdSession);
        assertEquals("AA-ZK-BL", createdSession.getLicensePlate());
        assertEquals("Java", createdSession.getStreetName());
    }

    @Test
    void testEndParking() {
        ParkingDuration duration = new ParkingDuration();

        duration.setLicensePlate("AA-ZK-BL");
        duration.setStreetName("Java");
        duration.setStartTime(LocalDateTime.of(2024, 7, 18, 14, 0));
        duration.setEndTime(null);
        StreetPricing streetPricing= new StreetPricing();
        streetPricing.setStreetPricing(10);
        streetPricing.setStreetName("Java");

        when(parkingDurationRepository.findByLicensePlateAndEndTimeIsNull("AA-ZK-BL")).thenReturn(Optional.of(duration));
        when(streetPricingRepository.findByStreetName("Java")).thenReturn(Optional.of(streetPricing));
        when(parkingDurationRepository.save(any(ParkingDuration.class))).thenReturn(duration);

        double amount = parkingService.endParking("AA-ZK-BL");

        assertTrue(amount > 0);
        assertNotNull(duration.getEndTime());
    }

    @Test
    void testEndParking_NotFound() {
        when(parkingDurationRepository.findByLicensePlateAndEndTimeIsNull("AA-ZK-BL")).thenReturn(Optional.empty());

        assertThrows(ParkingDurationNotFoundException.class, () -> parkingService.endParking("AA-ZK-BL"));
    }

    @Test
    void testStreetPricing_NotFound(){
        ParkingDuration duration = new ParkingDuration();

        duration.setLicensePlate("AA-ZK-BL");
        duration.setStreetName("Java");
        duration.setStartTime(LocalDateTime.of(2024, 7, 18, 14, 0));
        duration.setEndTime(null);
        when(parkingDurationRepository.save(any(ParkingDuration.class))).thenReturn(duration);
        when(parkingDurationRepository.findByLicensePlateAndEndTimeIsNull("AA-ZK-BL")).thenReturn(Optional.of(duration));
        when(streetPricingRepository.findByStreetName("AA-ZK-BL")).thenReturn(Optional.empty());
        assertThrows(IllegalStateException.class, ()->parkingService.endParking("AA-ZK-BL"));
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
