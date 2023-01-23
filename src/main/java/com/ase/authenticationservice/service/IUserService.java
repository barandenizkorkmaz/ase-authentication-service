package com.ase.authenticationservice.service;

import com.ase.authenticationservice.data.dto.UserDto;
import com.ase.authenticationservice.data.request.UserRequest;

import java.util.List;

public interface IUserService {
    void createUser(UserRequest registerRequest);
    void updateUser(String id, UserRequest updateRequest);
    List<UserDto> getUsers();
    void deleteUserById(String userId);

    UserDto getUser(String userId);
}
