package com.ase.authenticationservice.security.config;

import com.ase.authenticationservice.data.entity.UserType;
import com.ase.authenticationservice.security.CustomUserDetailsService;
import com.ase.authenticationservice.security.SecurityConstants;
import com.ase.authenticationservice.security.filter.AuthRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(customUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
        http
                .cors().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/**").authenticated()
                .antMatchers("/user/**").permitAll()
                .and()
                .httpBasic()
                .and()
                .sessionManagement().disable();

         */
        http
                .cors().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(SecurityConstants.REGISTER_PATH, SecurityConstants.LOGIN_PATH).permitAll()
                .antMatchers(
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/v3/api-docs/**"
                ).permitAll()
                .antMatchers("/user/**").hasAuthority(UserType.DISPATCHER.name())
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
