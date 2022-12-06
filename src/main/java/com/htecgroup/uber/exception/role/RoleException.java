package com.htecgroup.uber.exception.role;

import com.htecgroup.uber.exception.UberBaseException;
import org.springframework.http.HttpStatus;

public class RoleException extends UberBaseException {
    protected RoleException(String message, HttpStatus status) {
        super(message, status);
    }
}
