package com.htecgroup.uber.exception.driver;

import org.springframework.http.HttpStatus;

public class DriverNotFoundException extends DriverException {

    private static final String MESSAGE = "Driver not found";

    public DriverNotFoundException() {
        super(MESSAGE, HttpStatus.NOT_FOUND);
    }
}
