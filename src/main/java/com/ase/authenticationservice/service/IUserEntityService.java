package com.ase.authenticationservice.service;

import com.ase.authenticationservice.data.entity.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IUserEntityService {
    User getUser(String email) throws UsernameNotFoundException;

}
