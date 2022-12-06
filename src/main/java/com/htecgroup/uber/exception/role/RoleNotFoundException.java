package com.htecgroup.uber.exception.role;

import org.springframework.http.HttpStatus;

public class RoleNotFoundException extends RoleException {

    private static final String MESSAGE = "Role not found";

    public RoleNotFoundException() {
        super(MESSAGE, HttpStatus.NOT_FOUND);
    }
}
