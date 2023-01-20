package com.ase.authenticationservice.service;

import com.ase.authenticationservice.data.entity.User;
import com.ase.authenticationservice.data.entity.UserType;
import com.ase.authenticationservice.data.request.LoginRequest;
import com.ase.authenticationservice.data.response.LoginResponse;
import com.ase.authenticationservice.security.CustomUserDetails;
import com.ase.authenticationservice.security.CustomUserDetailsService;
import com.ase.authenticationservice.security.SecurityConstants;
import com.ase.authenticationservice.security.jwt.JwtUtil;
import com.netflix.discovery.converters.Auto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder bcryptPasswordEncoder;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    public ResponseEntity<LoginResponse> authenticateUser(LoginRequest loginRequest) {
        // TODO: Get the username and password by decoding the Base64 credential inside
        // the Basic Authentication
        // TODO: find if there is any user exists in the database based on the credential,
        // and authenticate the user using the Spring Authentication Manager

        final CustomUserDetails customUserDetails = customUserDetailsService.loadUserByUsername(loginRequest.getEmail());

        if (bcryptPasswordEncoder.matches(loginRequest.getPassword(), customUserDetails.getPassword())) {
            // bcryptPasswordEncoder.matches takes raw password as the first parameter!

            final String jwt = jwtUtil.generateToken(customUserDetails);

            LoginResponse loginResponse = LoginResponse.builder()
                    .id(customUserDetails.getId())
                    .userType(customUserDetails.getUserType())
                    .build();

            return ResponseEntity.ok()
                    .header(SecurityConstants.AUTHORIZATION, SecurityConstants.BEARER + jwt)
                    .body(loginResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
