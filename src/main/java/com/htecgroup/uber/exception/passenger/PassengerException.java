package com.htecgroup.uber.exception.passenger;

import com.htecgroup.uber.exception.UberBaseException;
import org.springframework.http.HttpStatus;

public class PassengerException extends UberBaseException {

    public PassengerException(String message, HttpStatus status) {
        super(message, status);
    }
}
