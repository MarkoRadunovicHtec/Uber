package com.htecgroup.uber.exception.jwt;

import com.htecgroup.uber.exception.UberBaseException;
import org.springframework.http.HttpStatus;

public class JwtException extends UberBaseException {

    protected JwtException(String message, HttpStatus status) {
        super(message, status);
    }
}
