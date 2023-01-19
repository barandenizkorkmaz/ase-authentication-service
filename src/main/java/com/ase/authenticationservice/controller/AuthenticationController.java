package com.ase.authenticationservice.controller;

import com.ase.authenticationservice.data.request.LoginRequest;
import com.ase.authenticationservice.data.response.LoginResponse;
import com.ase.authenticationservice.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    @PreAuthorize("hasAuthority('DISPATCHER') or hasAuthority('DELIVERER')")
    // TODO: Implement Authentication of the user credentials
    public ResponseEntity<LoginResponse> login(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization, @RequestBody LoginRequest loginRequest){
        LoginResponse response = authenticationService.authenticateUser(authorization, loginRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
