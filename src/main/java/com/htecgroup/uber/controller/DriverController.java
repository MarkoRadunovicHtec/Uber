package com.htecgroup.uber.controller;

import com.htecgroup.uber.model.request.DriverInputRequest;
import com.htecgroup.uber.model.request.DriverStatusAvailableRequest;
import com.htecgroup.uber.model.request.PricePerKmChangeRequest;
import com.htecgroup.uber.model.response.DriverResponse;
import com.htecgroup.uber.service.DriverService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/drivers")
@AllArgsConstructor
@Log4j2
@Tag(name = "Driver API", description = "Driver related operations")
public class DriverController {

    private DriverService driverService;

    @PreAuthorize("hasAuthority(T(com.htecgroup.uber.model.entity.RoleEntity).ROLE_GUEST)")
    @PostMapping("/create")
    public ResponseEntity<DriverResponse> createDriverAccount(@RequestBody DriverInputRequest driverInputRequest) {

        ResponseEntity<DriverResponse> responseEntity =
                new ResponseEntity<>(driverService.createAccount(driverInputRequest), HttpStatus.OK);
        return responseEntity;
    }

    @PreAuthorize("hasAuthority(T(com.htecgroup.uber.model.entity.RoleEntity).ROLE_DRIVER)")
    @PutMapping("/{driverId}/ppk-change")
    public ResponseEntity<DriverResponse> editPricePerKm(@RequestBody PricePerKmChangeRequest ppkChangeRequest) {
        ResponseEntity<DriverResponse> responseEntity =
                new ResponseEntity<>(driverService.editPricePerKm(ppkChangeRequest), HttpStatus.OK);
        return responseEntity;
    }

    @PreAuthorize("hasAuthority(T(com.htecgroup.uber.model.entity.RoleEntity).ROLE_DRIVER)")
    @PutMapping("/{driverId}/set-available")
    public ResponseEntity<DriverResponse> setStatusAvailable(@RequestBody DriverStatusAvailableRequest driverStatusAvailableRequest, @PathVariable UUID driverId) {
        ResponseEntity<DriverResponse> responseEntity =
                new ResponseEntity<>(driverService.setStatusAvailable(driverStatusAvailableRequest, driverId), HttpStatus.OK);
        return responseEntity;
    }


}
