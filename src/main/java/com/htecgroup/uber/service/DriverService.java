package com.htecgroup.uber.service;

import com.htecgroup.uber.model.request.DriverInputRequest;
import com.htecgroup.uber.model.request.DriverStatusAvailableRequest;
import com.htecgroup.uber.model.request.PricePerKmChangeRequest;
import com.htecgroup.uber.model.response.DriverResponse;

import java.util.UUID;

public interface DriverService {

    DriverResponse createAccount(DriverInputRequest driverInputRequest);

    DriverResponse editPricePerKm(PricePerKmChangeRequest ppkChangeRequest);

    DriverResponse getDriver(UUID driverId);

    DriverResponse setStatusAvailable(DriverStatusAvailableRequest driverStatusAvailableRequest, UUID driverId);
}
