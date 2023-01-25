package com.ase.authenticationservice;

import com.ase.authenticationservice.data.entity.User;
import com.ase.authenticationservice.data.entity.UserType;
import com.ase.authenticationservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableMongoRepositories
@EnableEurekaClient
public class AuthenticationServiceApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if(userRepository.findAll().size() == 0){
			userRepository.save(new User("admin@tum.de", bCryptPasswordEncoder.encode("admin"), UserType.DISPATCHER));
		}
	}

}
