package com.htecgroup.uber.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class ErrorMessage {

    private List<String> messages;
    private int status;
    private String timestamp;

    public ErrorMessage(String message, int status, String timestamp) {
        this.messages = new ArrayList<>();
        this.messages.add(message);
        this.status = status;
        this.timestamp = timestamp;
    }
}
