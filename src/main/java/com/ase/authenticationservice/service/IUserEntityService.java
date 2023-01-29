package com.ase.authenticationservice.service;

import com.ase.authenticationservice.data.entity.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface IUserEntityService {
    void createUser(User user);
    void updateUser(User user);
    void deleteUser(String email);
    User getUser(String email) throws UsernameNotFoundException;
    List<User> getUsers();
    boolean isUserExists(String email);

    List<String> getEmailsByUserType(String userType);
}
