package com.htecgroup.uber.service;

import com.htecgroup.uber.model.request.UserRegisterRequest;
import com.htecgroup.uber.model.response.UserResponse;

public interface RegistrationService {
    UserResponse registerUser(UserRegisterRequest userRegisterRequest);
}
