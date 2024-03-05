package com.example.demo03.responsitoty;

import com.example.demo03.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmailIgnoreCase(String emailId);

    Optional<User> findByUsername(String username);
}
