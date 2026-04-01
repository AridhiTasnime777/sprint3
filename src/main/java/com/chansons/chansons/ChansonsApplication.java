package com.chansons.chansons;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.chansons.chansons.entities.Role;
import com.chansons.chansons.entities.User;
import com.chansons.chansons.services.UserService;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class ChansonsApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(ChansonsApplication.class, args);
    }
/* 
    @PostConstruct
    void init_users() {
        // Clean up first to avoid duplicates on restart
        userService.deleteAllusers();
        userService.deleteAllRoles();

        // Add roles
        userService.addRole(new Role(null, "ADMIN"));
        userService.addRole(new Role(null, "AGENT"));
        userService.addRole(new Role(null, "USER"));

        // Add users
        userService.saveUser(new User(null, "admin", "123", true, null));
        userService.saveUser(new User(null, "tasnime", "123", true, null));
        userService.saveUser(new User(null, "user1", "123", true, null));

        // Assign roles
        userService.addRoleToUser("admin", "ADMIN");
        userService.addRoleToUser("tasnime", "USER");
        userService.addRoleToUser("tasnime", "AGENT");
        userService.addRoleToUser("user1", "USER");
    }
*/
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Password Encoded BCRYPT:");
        System.out.println(passwordEncoder.encode("123"));
    }
}