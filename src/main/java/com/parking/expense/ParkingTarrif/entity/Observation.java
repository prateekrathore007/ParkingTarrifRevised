package com.parking.expense.ParkingTarrif.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;



@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Observation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String licensePlate;
    private String streetName;
    private LocalDate dateOfObservation;

}

