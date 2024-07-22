package com.parking.expense.ParkingTarrif.repository;



import com.parking.expense.ParkingTarrif.entity.ParkingDuration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ParkingSessionRepositoryTest {

    @Autowired
    private ParkingDurationRepository parkingDurationRepository;

    @Test
    void testFindByLicensePlateAndEndTimeIsNull() {
        ParkingDuration session = new ParkingDuration();
        session.setLicensePlate("ABC123");
        session.setStreetName("Java");
        session.setStartTime(LocalDateTime.now());
        parkingDurationRepository.save(session);

        Optional<ParkingDuration> foundSession = parkingDurationRepository.findByLicensePlateAndEndTimeIsNull("ABC123");

        assertTrue(foundSession.isPresent());
        assertEquals("ABC123", foundSession.get().getLicensePlate());
    }
}
