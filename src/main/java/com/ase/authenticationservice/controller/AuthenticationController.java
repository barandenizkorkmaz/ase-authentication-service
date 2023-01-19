package com.ase.authenticationservice.controller;

import com.ase.authenticationservice.data.request.LoginRequest;
import com.ase.authenticationservice.data.response.LoginResponse;
import com.ase.authenticationservice.service.AuthenticationService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    // TODO: Implement Authentication of the user credentials
    public ResponseEntity<LoginResponse> login(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization, @RequestBody LoginRequest loginRequest) throws Exception {
        return authenticationService.authenticateUser(authorization, loginRequest);
    }

}
