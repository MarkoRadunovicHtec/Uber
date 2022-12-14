package com.htecgroup.uber.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransportRequestRequest {

    double latitudeLocation;
    double longitudeLocation;
    double latitudeDestination;
    double longitudeDestination;
}
