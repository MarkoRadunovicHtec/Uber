package com.htecgroup.uber.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoggedUserResponse {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;

    @With
    private List<String> roles;
}
