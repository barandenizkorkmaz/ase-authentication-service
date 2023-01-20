package com.ase.authenticationservice.security.filter;

import com.ase.authenticationservice.security.CustomUserDetails;
import com.ase.authenticationservice.security.CustomUserDetailsService;
import com.ase.authenticationservice.security.SecurityConstants;
import com.ase.authenticationservice.security.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthRequestFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        //System.out.println("AuthRequestFilter: doFilterInternal");

        String username = null;
        String jwt = null;
        final String authHeader = request.getHeader(SecurityConstants.AUTHORIZATION);
        //System.out.println("Authenticate Header " + authHeader);

        if (authHeader != null && authHeader.startsWith(SecurityConstants.BEARER)) {
            // TODO: Get the JWT in the header.
            // If the JWT expires or not signed by us, send an error to the client
            // TODO: Extract the username from the JWT token.
            jwt = authHeader.replace(SecurityConstants.BEARER, "");
            if (jwtUtil.verifyJwtSignature(jwt)){
                username = jwtUtil.extractUsername(jwt);
            }
            else{
                response.sendError(HttpStatus.BAD_REQUEST.value(), "Failed to Verify JWT Token");
            }
        } else {
            // No valid authentication, No go
            if (authHeader!=null && !authHeader.startsWith("Basic")) {
                response.sendError(HttpStatus.BAD_REQUEST.value(), "No JWT Token or Basic Auth Info Found");
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // TODO: load a user from the database that has the same username
            // as in the JWT token.
            CustomUserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails.getUsername(),
                    userDetails.getPassword(),
                    userDetails.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            //authService.setAuthentication(userDetails, request);
            Authentication authContext = SecurityContextHolder.getContext().getAuthentication();
            /*
            System.out.println(String.format("Authenticate Token Set:\n"
                            + "Username: %s\n"
                            + "Password: %s\n"
                            + "Authority: %s\n",
                    authContext.getPrincipal(),
                    authContext.getCredentials(),
                    authContext.getAuthorities().toString()));
             */
        }
        filterChain.doFilter(request, response);
    }
}
