package com.chansons.chansons.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.chansons.chansons.entities.Role;

import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);
}