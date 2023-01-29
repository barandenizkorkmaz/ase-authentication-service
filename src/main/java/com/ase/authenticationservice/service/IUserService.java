package com.ase.authenticationservice.service;

import com.ase.authenticationservice.data.dto.UserDto;
import com.ase.authenticationservice.data.request.UserRequest;

import java.util.List;

public interface IUserService {
    void createUser(UserRequest registerRequest);
    void updateUser(String email, UserRequest updateRequest);
    void deleteUser(String email);
    UserDto getUser(String email);
    List<UserDto> getUsers();
    List<String> getEmailsByUserType(String userType);
}
