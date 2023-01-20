package com.ase.authenticationservice.service;

import com.ase.authenticationservice.data.entity.User;
import com.ase.authenticationservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserEntityService implements IUserEntityService{

    private final UserRepository userRepository;



    @Override
    public User createUser(User user) {
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new EntityExistsException("User with email " + user.getEmail() + "already exists");
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user){
        if(!userRepository.existsById(user.getId())){
            throw new UsernameNotFoundException("User not found");
        }
        return userRepository.save(user);
    }

    @Override
    public User getUser(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()) return user.get();
        else throw new UsernameNotFoundException("User not found.");
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void deleteUserById(String userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User getUserById(String id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) return user.get();
        else throw new EntityNotFoundException();
    }
}
