package com.htecgroup.uber.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

@Entity(name = "role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity implements Serializable {

    public static final String ROLE_GUEST = "role_guest";
    public static final String ROLE_DRIVER = "role_driver";
    public static final String ROLE_PASSENGER = "role_passenger";
    public static final String ROLE_ADMIN = "role_admin";

    @Id
    @GeneratedValue
    private UUID id;
    private String name;
}
