package com.parking.expense.ParkingTarrif.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class ParkingDurationNotFoundException  extends RuntimeException{


    public ParkingDurationNotFoundException(String message) {
        super(message);
    }
}
