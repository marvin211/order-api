package com.devmarvin.orderapi.domain.repository;

import com.devmarvin.orderapi.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username); //SELECT * FROM users WHERE username = ?
}
