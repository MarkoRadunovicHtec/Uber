package com.htecgroup.uber.service.impl;

import com.htecgroup.uber.model.dto.LoggedUserDto;
import com.htecgroup.uber.model.response.LoggedUserResponse;
import com.htecgroup.uber.service.CurrentUserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CurrentUserServiceImpl implements CurrentUserService {

    private ModelMapper modelMapper;

    @Override
    public LoggedUserDto getLoggedUser() {
        return (LoggedUserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public LoggedUserResponse getUserResponseForLoggedUser() {
        LoggedUserResponse loggedUserResponse =
                modelMapper.map(getLoggedUser(), LoggedUserResponse.class);
        return loggedUserResponse.withRoles(getLoggedUser().getRoleNames());
    }


}
