package com.htecgroup.uber.service.impl;

import com.htecgroup.uber.exception.driver.DriverNotFoundException;
import com.htecgroup.uber.exception.user.UserNotFoundException;
import com.htecgroup.uber.model.entity.DriverEntity;
import com.htecgroup.uber.model.entity.RoleEntity;
import com.htecgroup.uber.model.request.DriverInputRequest;
import com.htecgroup.uber.model.request.DriverStatusAvailableRequest;
import com.htecgroup.uber.model.request.PricePerKmChangeRequest;
import com.htecgroup.uber.model.response.DriverResponse;
import com.htecgroup.uber.repository.DriverRepository;
import com.htecgroup.uber.repository.UserRepository;
import com.htecgroup.uber.service.CurrentUserService;
import com.htecgroup.uber.service.DriverService;
import com.htecgroup.uber.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Log4j2
@AllArgsConstructor
public class DriverServiceImpl implements DriverService {

    private CurrentUserService currentUserService;
    private UserService userService;
    private DriverRepository driverRepository;
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Override
    public DriverResponse createAccount(DriverInputRequest driverInputRequest) {

        UUID currentUserId = currentUserService.getLoggedUser().getId();
        DriverEntity driverEntity = modelMapper.map(driverInputRequest, DriverEntity.class);
        driverEntity.setUser(userRepository.findById(currentUserId).orElseThrow(UserNotFoundException::new));
        driverEntity = driverRepository.save(driverEntity);
        userService.changeRole(currentUserId, RoleEntity.ROLE_DRIVER);

        return modelMapper.map(driverEntity, DriverResponse.class);
    }


    @Override
    public DriverResponse editPricePerKm(PricePerKmChangeRequest ppkChangeRequest) {

        UUID currentUserId = currentUserService.getLoggedUser().getId();
        DriverEntity driverEntity = driverRepository.findByUser(currentUserId).orElseThrow(DriverNotFoundException::new);
        modelMapper.map(ppkChangeRequest, driverEntity);
        driverRepository.save(driverEntity);

        return modelMapper.map(driverEntity, DriverResponse.class);
    }

    @Override
    public DriverResponse getDriver(UUID driverId) {

        DriverEntity driverEntity = driverRepository.findById(driverId).orElseThrow(DriverNotFoundException::new);
        return modelMapper.map(driverEntity, DriverResponse.class);
    }

    @Override
    public DriverResponse setStatusAvailable(DriverStatusAvailableRequest driverStatusAvailableRequest, UUID driverId) {

        DriverEntity driverEntity = driverRepository.findById(driverId).orElseThrow(DriverNotFoundException::new);
        modelMapper.map(driverStatusAvailableRequest, driverEntity);
        driverRepository.save(driverEntity);

        return modelMapper.map(driverEntity, DriverResponse.class);
    }


}
