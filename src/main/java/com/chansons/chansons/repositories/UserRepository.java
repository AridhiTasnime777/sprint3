package com.chansons.chansons.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.chansons.chansons.entities.User;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}