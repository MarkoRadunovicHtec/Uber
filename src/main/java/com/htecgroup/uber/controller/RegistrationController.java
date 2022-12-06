package com.htecgroup.uber.controller;

import com.htecgroup.uber.model.request.UserRegisterRequest;
import com.htecgroup.uber.model.response.UserResponse;
import com.htecgroup.uber.service.RegistrationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
@AllArgsConstructor
@Log4j2
@Tag(name = "Registration API", description = "Registration operations")
public class RegistrationController {

    private RegistrationService registrationService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(
            @RequestBody UserRegisterRequest userRegisterRequest) {

        ResponseEntity<UserResponse> responseEntity =
                new ResponseEntity<>(registrationService.registerUser(userRegisterRequest), HttpStatus.OK);
        return responseEntity;
    }
}
