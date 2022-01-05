package com.example.UserService.repo;

import com.example.UserService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for User Application
 */
@Repository
public interface UserServiceRepository extends JpaRepository<User,Integer> {
    public Optional<User> findUserByEmailId(String emailId);
}
