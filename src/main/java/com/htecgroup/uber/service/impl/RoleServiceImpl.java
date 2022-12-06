package com.htecgroup.uber.service.impl;

import com.htecgroup.uber.exception.role.RoleNotFoundException;
import com.htecgroup.uber.model.entity.RoleEntity;
import com.htecgroup.uber.repository.RoleRepository;
import com.htecgroup.uber.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Override
    public RoleEntity findByName(String roleName) {
        RoleEntity roleEntity = roleRepository.findByName(roleName).orElseThrow(RoleNotFoundException::new);
        return roleEntity;
    }
}
