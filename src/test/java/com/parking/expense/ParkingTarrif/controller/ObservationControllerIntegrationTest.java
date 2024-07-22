package com.parking.expense.ParkingTarrif.controller;

import com.parking.expense.ParkingTarrif.repository.ObservationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ObservationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObservationRepository observationRepository;

    @Test
    void testAddObservations() throws Exception {
        String observationsJson = "[{\"licensePlate\":\"ABC123\",\"streetName\":\"Java\",\"dateOfObservation\":\"2024-07-18\"}]";

        mockMvc.perform(post("/api/list/observations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(observationsJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].licensePlate").value("ABC123"))
                .andExpect(jsonPath("$[0].streetName").value("Java"))
                .andExpect(jsonPath("$[0].dateOfObservation").value("2024-07-18"));
    }
}
