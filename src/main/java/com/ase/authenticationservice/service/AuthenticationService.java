package com.ase.authenticationservice.service;

import com.ase.authenticationservice.data.entity.User;
import com.ase.authenticationservice.data.entity.UserType;
import com.ase.authenticationservice.data.request.LoginRequest;
import com.ase.authenticationservice.data.response.LoginResponse;
import com.ase.authenticationservice.security.CustomUserDetails;
import com.ase.authenticationservice.security.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder bcryptPasswordEncoder;

    private final CustomUserDetailsService customUserDetailsService;

    public ResponseEntity<LoginResponse> authenticateUser(String authorization, LoginRequest loginRequest) throws Exception {
        // TODO: Get the username and password by decoding the Base64 credential inside
        // the Basic Authentication
        // TODO: find if there is any user exists in the database based on the credential,
        // and authenticate the user using the Spring Authentication Manager
        String pair=new String(Base64.decodeBase64(authorization.substring(6)));
        String username=pair.split(":")[0];
        String password=pair.split(":")[1];
        System.out.println(username);
        System.out.println(password);

        CustomUserDetails customUserDetails = customUserDetailsService.loadUserByUsername(username);
        System.out.println(customUserDetails.getUsername());
        System.out.println(customUserDetails.getPassword());
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        authenticationManager.authenticate(authenticationToken);

        LoginResponse loginResponse = LoginResponse.builder()
                .id(customUserDetails.getId())
                .userType(customUserDetails.getUserType())
                .build();

        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }
}
