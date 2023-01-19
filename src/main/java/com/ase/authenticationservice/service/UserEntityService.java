package com.ase.authenticationservice.service;

import com.ase.authenticationservice.data.entity.User;
import com.ase.authenticationservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserEntityService implements IUserEntityService{

    private final UserRepository userRepository;

    @Override
    public User getUser(String email) throws UsernameNotFoundException{
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()) return user.get();
        else throw new UsernameNotFoundException("Username not found.");
    }
}
