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
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest){
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
    @GetMapping("/list/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") String id){
        return ResponseEntity.ok(userService.getUser(id));
    }


    @PostMapping("delete/{id}")
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable("id") String id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HttpStatus> updateUser(@PathVariable("id") String id, @Valid @RequestBody UserRequest updateRequest){
        try{
            userService.updateUser(id, updateRequest);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
