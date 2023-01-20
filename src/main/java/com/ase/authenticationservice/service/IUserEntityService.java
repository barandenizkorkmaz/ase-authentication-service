package com.ase.authenticationservice.service;

import com.ase.authenticationservice.data.entity.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface IUserEntityService {
    User createUser(User user);
    User updateUser(User user);
    User getUser(String email) throws UsernameNotFoundException;
    List<User> getUsers();
    void deleteUserById(String userId);
    User getUserById(String id);
    boolean isUpdateUserValid(String id, String email);
}
