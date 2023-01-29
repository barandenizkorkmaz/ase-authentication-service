package com.ase.authenticationservice.service;

import com.ase.authenticationservice.data.entity.User;
import com.ase.authenticationservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserEntityService implements IUserEntityService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public void createUser(User user) {
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new EntityExistsException("User with email " + user.getEmail() + " already exists");
        }
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user){
        userRepository.save(user);
    }

    @Override
    public User getUser(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()) return user.get();
        else throw new UsernameNotFoundException("User with email " + email + " not found");
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(String email) {
        userRepository.deleteByEmail(email);
    }

    @Override
    public boolean isUserExists(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent();
    }

    @Override
    public List<String> getEmailsByUserType(String userType) {
        List<String> emails = new ArrayList<>();
        List<User> users = userRepository.findAllByUserType(userType);
        for(User user: users){
            emails.add(user.getEmail());
        }
        return emails;
    }
}
