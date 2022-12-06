package com.htecgroup.uber.exception.driver;

import com.htecgroup.uber.exception.UberBaseException;
import org.springframework.http.HttpStatus;

public class DriverException extends UberBaseException {

    protected DriverException(String message, HttpStatus status) {
        super(message, status);
    }
}
