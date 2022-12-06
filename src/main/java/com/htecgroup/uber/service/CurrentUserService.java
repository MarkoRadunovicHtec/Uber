package com.htecgroup.uber.service;

import com.htecgroup.uber.model.dto.LoggedUserDto;
import com.htecgroup.uber.model.response.LoggedUserResponse;

public interface CurrentUserService {

    LoggedUserDto getLoggedUser();

    LoggedUserResponse getUserResponseForLoggedUser();
}
