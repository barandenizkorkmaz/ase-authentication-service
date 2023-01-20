package com.ase.authenticationservice.controller;

import com.ase.authenticationservice.data.dto.UserDto;
import com.ase.authenticationservice.data.request.LoginRequest;
import com.ase.authenticationservice.data.request.UserRequest;
import com.ase.authenticationservice.data.response.LoginResponse;
import com.ase.authenticationservice.service.AuthenticationService;
import com.ase.authenticationservice.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationService authenticationService;

    private final IUserService userService;

    @PostMapping("/login")
    //@PreAuthorize("hasAuthority('DISPATCHER') or hasAuthority('DELIVERER')")
    // TODO: Implement Authentication of the user credentials
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest){
        return authenticationService.authenticateUser(loginRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> register(@Valid @RequestBody UserRequest registerRequest){
        System.out.println("Registering user");
        System.out.println("Request Details");
        System.out.println(registerRequest.getEmail());
        System.out.println(registerRequest.getPassword());
        System.out.println(registerRequest.getUserType());
        userService.createUser(registerRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/list/all")
    public ResponseEntity<List<UserDto>> listAll(){
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @PostMapping("delete/{id}")
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable("id") String id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update")
    public ResponseEntity<HttpStatus> updateUser(@Valid @RequestBody UserRequest updateRequest){
        userService.updateUser(updateRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
