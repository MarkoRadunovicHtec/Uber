package com.htecgroup.uber.service;

import com.htecgroup.uber.model.request.PassengerInputRequest;
import com.htecgroup.uber.model.response.PassengerResponse;

public interface PassengerService {

    PassengerResponse createAccount(PassengerInputRequest passengerInputRequest);
}
