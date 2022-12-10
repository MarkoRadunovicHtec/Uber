package com.htecgroup.uber.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverInputRequest {

    private String age;
    private String firstName;
    private String lastName;
    private String gender;
    private String status;
    private String latitude;
    private String longitude;
    private String pricePerKm;
}
