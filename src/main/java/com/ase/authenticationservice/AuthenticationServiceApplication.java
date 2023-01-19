package com.ase.authenticationservice;

import com.ase.authenticationservice.data.entity.User;
import com.ase.authenticationservice.data.entity.UserType;
import com.ase.authenticationservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableMongoRepositories
@EnableEurekaClient
public class AuthenticationServiceApplication implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Only create test users when there is no user in DB
		if (userRepository.findAll().size() == 0) {
			// TODO: Create test users with hashed Bcrypt password and role
			userRepository.save(new User("dispatcher", "dispatcher@tum.de", bCryptPasswordEncoder.encode("dispatcher"), UserType.DISPATCHER));
			userRepository.save(new User("deliverer", "deliverer@tum.de", bCryptPasswordEncoder.encode("deliverer"), UserType.DELIVERER));
			userRepository.save(new User("customer", "customer@tum.de", bCryptPasswordEncoder.encode("customer"), UserType.CUSTOMER));
		}
	}
}
