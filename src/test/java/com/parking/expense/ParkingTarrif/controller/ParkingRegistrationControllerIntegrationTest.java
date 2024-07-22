package com.parking.expense.ParkingTarrif.controller;

import com.parking.expense.ParkingTarrif.entity.ParkingDuration;
import com.parking.expense.ParkingTarrif.repository.ParkingDurationRepository;
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
public class ParkingRegistrationControllerIntegrationTest {



    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ParkingDurationRepository parkingDurationRepository;

    @Test
    void testStartParking() throws Exception {
        mockMvc.perform(post("/api/parking/start")
                        .param("licensePlate", "XYZ123")
                        .param("streetName", "Spring")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.licensePlate").value("XYZ123"))
                .andExpect(jsonPath("$.streetName").value("Spring"));
    }

    @Test
    void testEndParking() throws Exception {
        ParkingDuration duration = new ParkingDuration();
        duration.setLicensePlate("XYZ123");
        duration.setStreetName("Spring");
        duration.setStartTime(LocalDateTime.now());
        parkingDurationRepository.save(duration);

        mockMvc.perform(post("/api/parking/end")
                        .param("licensePlate", "XYZ123")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
