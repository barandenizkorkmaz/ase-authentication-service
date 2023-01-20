package com.ase.authenticationservice.service;

import com.ase.authenticationservice.data.dto.UserDto;
import com.ase.authenticationservice.data.request.UserRequest;

import java.util.List;

public interface IUserService {
    UserDto createUser(UserRequest registerRequest);
    UserDto updateUser(UserRequest updateRequest);
    List<UserDto> getUsers();
    void deleteUserById(String userId);
}
