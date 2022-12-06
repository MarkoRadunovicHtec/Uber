package com.htecgroup.uber.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerInputRequest {

    private String age;
    private String gender;
    private String latitude;
    private String longitude;
}
