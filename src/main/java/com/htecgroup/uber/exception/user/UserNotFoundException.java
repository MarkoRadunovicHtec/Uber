package com.htecgroup.uber.exception.user;

import com.htecgroup.uber.exception.UberBaseException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends UberBaseException {

    private static final String MESSAGE = "User not found";

    public UserNotFoundException() {
        super(MESSAGE, HttpStatus.NOT_FOUND);
    }
}
