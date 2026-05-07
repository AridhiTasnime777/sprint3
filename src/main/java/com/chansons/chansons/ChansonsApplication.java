package com.chansons.chansons;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.chansons.chansons.entities.Role;
import com.chansons.chansons.entities.User;
import com.chansons.chansons.entities.Chanson;
import com.chansons.chansons.entities.Album;
import com.chansons.chansons.services.UserService;
import com.chansons.chansons.services.ChansonService;
import com.chansons.chansons.repositories.AlbumRepository;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class ChansonsApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ChansonService chansonService;

    @Autowired
    private AlbumRepository albumRepository;

    public static void main(String[] args) {
        SpringApplication.run(ChansonsApplication.class, args);
    }
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
        // userService.addRoleToUser("tasnime", "AGENT"); // Remove Agent from Tasnime
        userService.addRoleToUser("user1", "USER");

        // Add Sample Data if needed
        if (chansonService.getAllChansons().isEmpty()) {
            System.out.println("========== INITIALIZING SAMPLE DATA ==========");
            Album a1 = albumRepository.save(new Album("Rock 2024", "Un album de rock"));
            Album a2 = albumRepository.save(new Album("Pop Hits", "Les meilleurs hits pop"));
            System.out.println("Created Albums: " + a1.getNomAlb() + ", " + a2.getNomAlb());

            Chanson c1 = new Chanson("Summer Nights", "The Waves", new java.util.Date());
            c1.setAlbum(a1);
            chansonService.saveChanson(c1);

            Chanson c2 = new Chanson("Winter Wind", "Cold Play", new java.util.Date());
            c2.setAlbum(a1);
            chansonService.saveChanson(c2);

            Chanson c3 = new Chanson("City Lights", "Urban Beats", new java.util.Date());
            c3.setAlbum(a2);
            chansonService.saveChanson(c3);
            
            System.out.println("Created 3 Sample Songs.");
            System.out.println("==============================================");
        }
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Password Encoded BCRYPT:");
        System.out.println(passwordEncoder.encode("123"));
    }
}