package com.htecgroup.uber.exception;

import com.htecgroup.uber.model.response.ErrorMessage;
import com.htecgroup.uber.util.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler(value = {UberBaseException.class})
    public ResponseEntity<ErrorMessage> handleUserException(
            UberBaseException ex, WebRequest webRequest) {
        log.error("Handle CustomException. Error: {}", ex.getMessage());
        ErrorMessage errorMessage =
                new ErrorMessage(
                        ex.getMessage(), ex.getStatus().value(), DateTimeUtil.currentTimeFormatted());
        return new ResponseEntity<>(errorMessage, ex.getStatus());
    }
}
