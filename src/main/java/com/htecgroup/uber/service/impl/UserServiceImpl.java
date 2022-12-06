package com.htecgroup.uber.service.impl;

import com.htecgroup.uber.exception.user.UserNotFoundException;
import com.htecgroup.uber.model.entity.RoleEntity;
import com.htecgroup.uber.model.entity.UserEntity;
import com.htecgroup.uber.repository.UserRepository;
import com.htecgroup.uber.service.RoleService;
import com.htecgroup.uber.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Log4j2
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleService roleService;

    @Override
    public UserEntity findUserByEmail(String email) {
        UserEntity userEntity =
                userRepository.findUserByEmail(email).orElseThrow(UserNotFoundException::new);
        return userEntity;
    }

    @Override
    public void changeRole(UUID currentUserId, String roleName) {
        UserEntity userEntity = userRepository.findById(currentUserId).orElseThrow(UserNotFoundException::new);
        RoleEntity newRole = roleService.findByName(roleName);
        UserEntity UserWithRoleChanged = userEntity.withRole(newRole);
        userRepository.save(UserWithRoleChanged);
    }


}
