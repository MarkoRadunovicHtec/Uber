package com.htecgroup.uber.controller;

import com.htecgroup.uber.model.request.TransportRequestRequest;
import com.htecgroup.uber.service.TransportService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/transport")
@AllArgsConstructor
@Log4j2
@Tag(name = "Transport API", description = "Transport related operations")
public class TransportController {

    TransportService transportService;

    @PreAuthorize("hasAuthority(T(com.htecgroup.uber.model.entity.RoleEntity).ROLE_PASSENGER)")
    @PutMapping("/{driverId}/request-make")
    public ResponseEntity<Boolean> makeTransportRequest(@RequestBody TransportRequestRequest transportRequestRequest, @PathVariable UUID driverId) {
        transportService.makeTransportRequest(transportRequestRequest, driverId);
        return ResponseEntity.ok(true);
    }
}
