package com.parking.expense.ParkingTarrif.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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


    @NotBlank(message = "License Plate is Mandatory")
    @Pattern(regexp = "^[A-Z]{2}-[A-Z]{2}-[A-Z]{2}$", message = "Kindly enter license plate in correct format")
    private String licensePlate;

    @NotBlank(message ="Street Name is Mandatory")
    private String streetName;
    private LocalDate dateOfObservation;

}

