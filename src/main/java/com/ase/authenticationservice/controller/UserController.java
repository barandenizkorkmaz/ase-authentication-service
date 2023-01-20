package com.ase.authenticationservice.controller;

import com.ase.authenticationservice.data.request.LoginRequest;
import com.ase.authenticationservice.data.response.LoginResponse;
import com.ase.authenticationservice.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    //@PreAuthorize("hasAuthority('DISPATCHER') or hasAuthority('DELIVERER')")
    // TODO: Implement Authentication of the user credentials
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        return authenticationService.authenticateUser(loginRequest);
    }

}
