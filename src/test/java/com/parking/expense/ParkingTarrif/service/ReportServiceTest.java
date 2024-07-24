package com.parking.expense.ParkingTarrif.service;




import com.parking.expense.ParkingTarrif.entity.Observation;
import com.parking.expense.ParkingTarrif.entity.ParkingDuration;
import com.parking.expense.ParkingTarrif.repository.ObservationRepository;
import com.parking.expense.ParkingTarrif.repository.ParkingDurationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ReportServiceTest {

    @InjectMocks
    private ReportService reportService;

    @Mock
    private ObservationRepository observationRepository;

    @Mock
    private ParkingDurationRepository sessionRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerateReport() {
        Observation observation1 = new Observation();
        observation1.setLicensePlate("ABC123");
        observation1.setStreetName("Java");
        observation1.setDateOfObservation(LocalDate.of(2024, 7, 19));

        Observation observation2 = new Observation();
        observation2.setLicensePlate("XYZ456");
        observation2.setStreetName("Spring");
        observation2.setDateOfObservation(LocalDate.of(2024, 7, 19));
        List<Observation> observations= new ArrayList<>();
        observations.add(observation1);
        observations.add(observation2);
        when(observationRepository.findAll()).thenReturn(Arrays.asList(observation1, observation2));
        when(sessionRepository.findByLicensePlateAndEndTimeIsNull("ABC123")).thenReturn(Optional.of(new ParkingDuration()));
        when(sessionRepository.findByLicensePlateAndEndTimeIsNull("XYZ456")).thenReturn(Optional.of(new ParkingDuration()));

       reportService.generateReport();



        // Verify the CSV file contents
        verifyCsvFile("unregistered_parking_observations.csv", observations);
    }

    private void verifyCsvFile(String fileName, List<Observation> expectedObservations) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String header = reader.readLine();
            assertEquals("LicensePlate,StreetName,DateOfObservation", header);

            for (Observation observation : expectedObservations) {
                String line = reader.readLine();
                String[] parts = line.split(",");
                assertEquals(observation.getLicensePlate(), parts[0]);
                assertEquals(observation.getStreetName(), parts[1]);
                assertEquals(observation.getDateOfObservation().toString(), parts[2]);
            }

        } catch (IOException e) {
            System.out.println("Exception Occurred "+e.getMessage());
        }
    }
}
