package com.parking.expense.ParkingTarrif.repository;



import com.parking.expense.ParkingTarrif.entity.Observation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ObservationRepositoryTest {

    @Autowired
    private ObservationRepository observationRepository;

    @Test
    void testSaveAndFind() {
        Observation observation = new Observation();
        observation.setLicensePlate("DEF456");
        observation.setStreetName("Spring");
        observation.setDateOfObservation(LocalDate.of(2024, 7, 18));
        observationRepository.save(observation);

        Observation foundObservation = observationRepository.findById(observation.getId()).orElse(null);

        assertNotNull(foundObservation);
        assertEquals("DEF456", foundObservation.getLicensePlate());
        assertEquals("Spring", foundObservation.getStreetName());
    }
}