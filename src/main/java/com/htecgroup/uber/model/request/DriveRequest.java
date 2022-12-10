package com.htecgroup.uber.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriveRequest {

    double latitude;
    double longitude;
    double latitudeDestination;
    double longitudeDestination;
}
