package com.htecgroup.uber.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private String firstName;
    private String lastName;
    private String email;
    private String encryptedPassword;

    @Column(insertable = false, updatable = false)
    private LocalDateTime createdOn;

    @With
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    @With
    private RoleEntity role;
}
