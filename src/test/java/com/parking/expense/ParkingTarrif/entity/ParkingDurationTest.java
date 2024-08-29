package com.parking.expense.ParkingTarrif.entity;


import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ParkingDurationTest {

    @Test
    void testParkingSession() {
        ParkingDuration session = new ParkingDuration();
        session.setId(1L);
        session.setLicensePlate("AB-ZK-BL");
        session.setStreetName("Java");
        session.setStartTime(LocalDateTime.of(2024, 7, 18, 14, 0));
        session.setEndTime(LocalDateTime.of(2024, 7, 18, 14, 55));

        assertEquals(1L, session.getId());
        assertEquals("AB-ZK-BL", session.getLicensePlate());
        assertEquals("Java", session.getStreetName());
        assertEquals(LocalDateTime.of(2024, 7, 18, 14, 0), session.getStartTime());
        assertEquals(LocalDateTime.of(2024, 7, 18, 14, 55), session.getEndTime());
    }
}
