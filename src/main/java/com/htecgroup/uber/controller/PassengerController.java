package com.htecgroup.uber.controller;

import com.htecgroup.uber.model.request.PassengerInputRequest;
import com.htecgroup.uber.model.response.PassengerResponse;
import com.htecgroup.uber.service.PassengerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/passengers")
@AllArgsConstructor
@Log4j2
@Tag(name = "Passenger API", description = "Passenger related operations")
public class PassengerController {

    PassengerService passengerService;

    @PreAuthorize("hasAuthority(T(com.htecgroup.uber.model.entity.RoleEntity).ROLE_GUEST)")
    @PostMapping("/create")
    public ResponseEntity<PassengerResponse> createPassengerAccount(@RequestBody PassengerInputRequest passengerInputRequest) {

        ResponseEntity<PassengerResponse> responseEntity =
                new ResponseEntity<>(passengerService.createAccount(passengerInputRequest), HttpStatus.OK);
        return responseEntity;
    }
}
