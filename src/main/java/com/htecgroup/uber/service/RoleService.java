package com.htecgroup.uber.service;

import com.htecgroup.uber.model.entity.RoleEntity;

public interface RoleService {

    public RoleEntity findByName(String roleName);
}
