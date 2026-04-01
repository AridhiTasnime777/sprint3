package com.chansons.chansons.services;

import com.chansons.chansons.entities.Role;
import com.chansons.chansons.entities.User;
public interface UserService {
void deleteAllusers();
void deleteAllRoles();
User saveUser(User user);
User findUserByUsername (String username);
Role addRole(Role role);
User addRoleToUser(String username, String rolename);
}

