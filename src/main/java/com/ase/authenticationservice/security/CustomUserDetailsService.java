package com.ase.authenticationservice.security;

import com.ase.authenticationservice.data.entity.User;
import com.ase.authenticationservice.repository.UserRepository;
import com.ase.authenticationservice.service.IUserEntityService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserEntityService userEntityService;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO: Call the repository to find the user from a given username
        // TODO: return a Spring User with the
        // username, password and authority that we retrieved above
        User user = userEntityService.getUser(username);
        return CustomUserDetails.create(user);
    }
}
