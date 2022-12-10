package com.htecgroup.uber.service;

import com.htecgroup.uber.model.request.DriveRequest;
import com.htecgroup.uber.model.request.PassengerInputRequest;
import com.htecgroup.uber.model.response.PassengerResponse;

import java.util.UUID;

public interface PassengerService {

    PassengerResponse createAccount(PassengerInputRequest passengerInputRequest);

    void createDriveRequest(DriveRequest driveRequest, UUID driverId);
}
