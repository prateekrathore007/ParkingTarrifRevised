package com.parking.expense.ParkingTarrif.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StreetPricingTest {

    @Test
    void testStreetPricing() {
        StreetPricing streetPricing= new StreetPricing();
        streetPricing.setId(1L);
        streetPricing.setStreetName("AA-ZK-BL");
        streetPricing.setStreetPricing(12);


        assertEquals(1L, streetPricing.getId());
        assertEquals(12, streetPricing.getStreetPricing());
        assertEquals("AA-ZK-BL", streetPricing.getStreetName());

    }
}
