package com.htecgroup.uber.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity(name = "drive_request")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DriveRequestEntity {

    @Id
    @GeneratedValue
    UUID id;

    double latitudeLocation;
    double longitudeLocation;
    double latitudeDestination;
    double longitudeDestination;

}
