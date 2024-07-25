package com.parking.expense.ParkingTarrif.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ObservationTest {

    @Test
    void testObservation() {
        Observation observation = new Observation();
        observation.setId(1L);
        observation.setLicensePlate("DEF456");
        observation.setStreetName("Spring");
        observation.setDateOfObservation(LocalDate.of(2024, 7, 18));

        assertEquals(1L, observation.getId());
        assertEquals("DEF456", observation.getLicensePlate());
        assertEquals("Spring", observation.getStreetName());
        assertEquals(LocalDate.of(2024, 7, 18), observation.getDateOfObservation());
    }
}
