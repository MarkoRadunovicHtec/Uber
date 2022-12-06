package com.htecgroup.uber.service.impl;

import com.htecgroup.uber.exception.user.UserNotFoundException;
import com.htecgroup.uber.model.entity.PassengerEntity;
import com.htecgroup.uber.model.entity.RoleEntity;
import com.htecgroup.uber.model.request.PassengerInputRequest;
import com.htecgroup.uber.model.response.PassengerResponse;
import com.htecgroup.uber.repository.PassengerRepository;
import com.htecgroup.uber.repository.UserRepository;
import com.htecgroup.uber.service.CurrentUserService;
import com.htecgroup.uber.service.PassengerService;
import com.htecgroup.uber.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Log4j2
@AllArgsConstructor
public class PassengerServiceImpl implements PassengerService {

    PassengerRepository passengerRepository;
    CurrentUserService currentUserService;
    UserRepository userRepository;
    UserService userService;
    ModelMapper modelMapper;

    @Override
    public PassengerResponse createAccount(PassengerInputRequest passengerInputRequest) {

        UUID currentUserId = currentUserService.getLoggedUser().getId();
        PassengerEntity passengerEntity = modelMapper.map(passengerInputRequest, PassengerEntity.class);
        passengerEntity.setUserEntity(userRepository.findById(currentUserId).orElseThrow(UserNotFoundException::new));
        passengerEntity = passengerRepository.save(passengerEntity);
        userService.changeRole(currentUserId, RoleEntity.ROLE_PASSENGER);

        return modelMapper.map(passengerEntity, PassengerResponse.class);
    }
}
