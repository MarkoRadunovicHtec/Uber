package com.htecgroup.uber.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverStatusAvailableRequest {

    private String status;
    private double latitude;
    private double longitude;
}
