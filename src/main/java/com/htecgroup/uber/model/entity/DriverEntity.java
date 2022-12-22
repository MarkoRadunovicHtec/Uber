package com.htecgroup.uber.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "driver")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DriverEntity {

    public static final String STATUS_AVAILABLE = "available";
    public static final String STATUS_BUSY = "busy";

    @Id
    @GeneratedValue
    private UUID id;

    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String status;
    private double latitude;
    private double longitude;
    private double pricePerKm;
    private double rating;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}
