package com.parking.expense.ParkingTarrif.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;



@Entity
public class Observation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String licensePlate;
    private String streetName;
    private LocalDate dateOfObservation;

    public Long getId() {
        return id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public LocalDate getDateOfObservation() {
        return dateOfObservation;
    }

    public void setDateOfObservation(LocalDate dateOfObservation) {
        this.dateOfObservation = dateOfObservation;
    }


}

