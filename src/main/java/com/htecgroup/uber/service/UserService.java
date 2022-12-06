package com.htecgroup.uber.service;

import com.htecgroup.uber.model.entity.UserEntity;

import java.util.UUID;

public interface UserService {

    UserEntity findUserByEmail(String email);

    void changeRole(UUID currentUserId, String roleName);
}
