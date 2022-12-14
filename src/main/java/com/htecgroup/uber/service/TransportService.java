package com.htecgroup.uber.service;

import com.htecgroup.uber.model.request.TransportRequestRequest;

import java.util.UUID;

public interface TransportService {

    public void makeTransportRequest(TransportRequestRequest transportRequestRequest, UUID driverId);
}
