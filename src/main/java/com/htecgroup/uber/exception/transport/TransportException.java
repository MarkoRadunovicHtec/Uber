package com.htecgroup.uber.exception.transport;

import com.htecgroup.uber.exception.UberBaseException;
import org.springframework.http.HttpStatus;

public class TransportException extends UberBaseException {

    public TransportException(String message, HttpStatus status) {
        super(message, status);
    }
}
