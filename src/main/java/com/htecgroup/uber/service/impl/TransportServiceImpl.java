package com.htecgroup.uber.service.impl;

import com.htecgroup.uber.exception.driver.DriverNotFoundException;
import com.htecgroup.uber.exception.passenger.PassengerNotFoundException;
import com.htecgroup.uber.model.entity.TransportRequestEntity;
import com.htecgroup.uber.model.request.TransportRequestRequest;
import com.htecgroup.uber.repository.DriverRepository;
import com.htecgroup.uber.repository.PassengerRepository;
import com.htecgroup.uber.repository.TransportRequestRepository;
import com.htecgroup.uber.service.CurrentUserService;
import com.htecgroup.uber.service.TransportService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Log4j2
@AllArgsConstructor
public class TransportServiceImpl implements TransportService {

    TransportRequestRepository transportRequestRepository;
    CurrentUserService currentUserService;
    DriverRepository driverRepository;
    PassengerRepository passengerRepository;
    ModelMapper modelMapper;

    @Override
    public void makeTransportRequest(TransportRequestRequest transportRequestRequest, UUID driverId) {

        UUID currentUserId = currentUserService.getLoggedUser().getId();

        TransportRequestEntity transportRequest = modelMapper.map(transportRequestRequest, TransportRequestEntity.class);
        transportRequest.setDriver(driverRepository.findById(driverId).orElseThrow(DriverNotFoundException::new));
        transportRequest.setPassenger(passengerRepository.findByUserEntityId(currentUserId).orElseThrow(PassengerNotFoundException::new));
        transportRequest.setStatus(TransportRequestEntity.STATUS_PENDING);

        transportRequestRepository.save(transportRequest);
    }
}
