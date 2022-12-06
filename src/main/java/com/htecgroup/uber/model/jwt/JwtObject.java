package com.htecgroup.uber.model.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class JwtObject {
    UUID id;
    String email;
}
