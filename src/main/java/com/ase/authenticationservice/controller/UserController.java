package com.ase.authenticationservice.controller;

import com.ase.authenticationservice.data.dto.UserDto;
import com.ase.authenticationservice.data.request.LoginRequest;
import com.ase.authenticationservice.data.request.UserRequest;
import com.ase.authenticationservice.data.response.GetUserInfoResponse;
import com.ase.authenticationservice.service.AuthenticationService;
import com.ase.authenticationservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private IUserService userService;

    @GetMapping("")
    public ResponseEntity<HttpStatus> startSession(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    //@PreAuthorize("hasAuthority('DISPATCHER') or hasAuthority('DELIVERER')")
    // TODO: Implement Authentication of the user credentials
    public ResponseEntity<HttpStatus> login(@Valid @RequestBody LoginRequest loginRequest){
        return authenticationService.authenticateUser(loginRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> register(@Valid @RequestBody UserRequest registerRequest){
        userService.createUser(registerRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/list/all")
    public ResponseEntity<List<UserDto>> listAll(){
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/list/emails/deliverer")
    public ResponseEntity<List<String>> getEmailsDeliverers(){
        return new ResponseEntity<>(userService.getEmailsByUserType("DELIVERER"),HttpStatus.OK);
    }

    @GetMapping("/list/emails/customer")
    public ResponseEntity<List<String>> getEmailsCustomer(){
        return new ResponseEntity<>(userService.getEmailsByUserType("CUSTOMER"),HttpStatus.OK);
    }

    @GetMapping("/info/{email}")
    public ResponseEntity<GetUserInfoResponse> getUser(@PathVariable("email") String email){
        UserDto userDto = userService.getUser(email);
        return ResponseEntity.ok(GetUserInfoResponse.builder().userType(userDto.getUserType()).build());
    }

    @PostMapping("delete/{email}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("email") String email) {
        userService.deleteUser(email);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{email}")
    public ResponseEntity<HttpStatus> updateUser(@PathVariable("email") String email, @Valid @RequestBody UserRequest updateRequest){
        try{
            userService.updateUser(email, updateRequest);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
