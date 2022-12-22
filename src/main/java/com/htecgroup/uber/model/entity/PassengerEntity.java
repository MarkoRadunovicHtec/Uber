package com.htecgroup.uber.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "passenger")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PassengerEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private double latitude;
    private double longitude;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}
