package com.htecgroup.uber.service.impl;

import com.htecgroup.uber.model.entity.RoleEntity;
import com.htecgroup.uber.model.entity.UserEntity;
import com.htecgroup.uber.model.request.UserRegisterRequest;
import com.htecgroup.uber.model.response.UserResponse;
import com.htecgroup.uber.repository.UserRepository;
import com.htecgroup.uber.service.RegistrationService;
import com.htecgroup.uber.service.RoleService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepository userRepository;
    private RoleService roleService;
    private ModelMapper modelMapper;

    @Override
    public UserResponse registerUser(UserRegisterRequest userRegisterRequest) {
        UserEntity userEntity = modelMapper.map(userRegisterRequest, UserEntity.class);
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userRegisterRequest.getPassword()));
        userEntity.setRole(roleService.findByName(RoleEntity.ROLE_GUEST));
        userEntity = userRepository.save(userEntity.withIsActive(false));
        UserResponse userResponse = modelMapper.map(userEntity, UserResponse.class);
        return modelMapper.map(userEntity, UserResponse.class);
    }
}
