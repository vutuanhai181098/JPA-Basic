package com.example.JPABasic.repository;

import com.example.JPABasic.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByNameContainingIgnoreCase(String name);
}
