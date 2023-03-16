package com.example.hospitalreservation.users.repository;

import com.example.hospitalreservation.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, String> {
    Optional<Users> findByUserId(String username);
}
