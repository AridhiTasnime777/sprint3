package com.chansons.chansons.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chansons.chansons.entities.Album;
import com.chansons.chansons.repositories.AlbumRepository;

@RestController
@RequestMapping("/api/alb")
@CrossOrigin("*")
public class AlbumRestController {

    @Autowired
    AlbumRepository albumRepository;

    @GetMapping
    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    @GetMapping("/{id}")
    public Album getAlbumById(@PathVariable Long id) {
        return albumRepository.findById(id).orElse(null);
    }
}
