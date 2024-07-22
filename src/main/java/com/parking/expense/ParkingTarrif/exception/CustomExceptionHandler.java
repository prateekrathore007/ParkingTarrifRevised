package com.parking.expense.ParkingTarrif.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ParkingDurationNotFoundException.class)
    public ResponseEntity<String> handleParkingDurationNotFound(ParkingDurationNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(Exception ex){
        return  new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
