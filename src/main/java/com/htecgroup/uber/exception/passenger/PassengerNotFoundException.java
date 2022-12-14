package com.htecgroup.uber.exception.passenger;

import org.springframework.http.HttpStatus;

public class PassengerNotFoundException extends PassengerException {

    private static final String MESSAGE = "Passenger not found";

    public PassengerNotFoundException() {
        super(MESSAGE, HttpStatus.NOT_FOUND);
    }
}
