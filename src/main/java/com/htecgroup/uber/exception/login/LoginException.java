package com.htecgroup.uber.exception.login;

import com.htecgroup.uber.exception.UberBaseException;
import org.springframework.http.HttpStatus;

public class LoginException extends UberBaseException {

    protected LoginException(String message, HttpStatus status) {
        super(message, status);
    }
}
