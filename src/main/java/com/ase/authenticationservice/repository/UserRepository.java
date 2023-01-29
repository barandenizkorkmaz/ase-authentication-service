package com.ase.authenticationservice.repository;

import com.ase.authenticationservice.data.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
    List<User> findAllByUserType(String userType);
    void deleteByEmail(String email);
}
