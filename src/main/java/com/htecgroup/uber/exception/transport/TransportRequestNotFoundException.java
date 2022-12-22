package com.htecgroup.uber.exception.transport;

import org.springframework.http.HttpStatus;

public class TransportRequestNotFoundException extends TransportException {

    private static final String MESSAGE = "Transport request not found";

    public TransportRequestNotFoundException() {
        super(MESSAGE, HttpStatus.NOT_FOUND);
    }
}
