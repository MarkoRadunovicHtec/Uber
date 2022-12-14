package com.htecgroup.uber.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "transport_request")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransportRequestEntity {

    public static final String STATUS_PENDING = "pending";
    public static final String STATUS_DECLINED = "declined";
    public static final String STATUS_ACCEPTED = "accepted";

    @Id
    @GeneratedValue
    UUID id;

    double latitudeLocation;
    double longitudeLocation;
    double latitudeDestination;
    double longitudeDestination;
    @Column(insertable = false, updatable = false)
    private LocalDateTime createdOn;
    String status;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private DriverEntity driver;

    @ManyToOne
    @JoinColumn(name = "passenger_id", nullable = false)
    private PassengerEntity passenger;
}
