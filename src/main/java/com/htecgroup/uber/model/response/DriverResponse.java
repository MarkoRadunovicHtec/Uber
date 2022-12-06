package com.htecgroup.uber.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverResponse {

    private UUID id;
    private int age;
    private String gender;
    private String status;
    private double latitude;
    private double longitude;
    private double pricePerKm;
    private double rating;
}
