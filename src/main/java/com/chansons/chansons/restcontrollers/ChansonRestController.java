package com.chansons.chansons.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chansons.chansons.dto.ChansonDTO;
import com.chansons.chansons.entities.Chanson;
import com.chansons.chansons.services.ChansonServicelmpm;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ChansonRestController {

    @Autowired
    ChansonServicelmpm chansonService;

    @GetMapping("/chansons")
    public List<ChansonDTO> getAllChansons() {
        return chansonService.getAllChansonsDTO();
    }

    @GetMapping("/chansons/{id}")
    public ChansonDTO getChanson(@PathVariable Long id) {
        Chanson ch = chansonService.getChanson(id);
        return chansonService.convertEntityToDto(ch);
    }

    @PostMapping("/chansons")
    public ChansonDTO createChanson(@RequestBody ChansonDTO dto) {
        Chanson ch = chansonService.convertDtoToEntity(dto);
        Chanson saved = chansonService.saveChanson(ch);
        return chansonService.convertEntityToDto(saved);
    }

    @PutMapping("/chansons")
    public ChansonDTO updateChanson(@RequestBody ChansonDTO dto) {
        Chanson ch = chansonService.convertDtoToEntity(dto);
        Chanson saved = chansonService.updateChanson(ch);
        return chansonService.convertEntityToDto(saved);
    }

    @DeleteMapping("/chansons/{id}")
    public void deleteChanson(@PathVariable Long id) {
        chansonService.deleteChansonById(id);
    }

    @GetMapping("/chansons/byName/{nom}")
    public List<ChansonDTO> findByName(@PathVariable String nom) {
        return chansonService.findByTitleContains(nom)
                .stream()
                .map(chansonService::convertEntityToDto)
                .toList();
    }

    @GetMapping("/chansons/byAlbum/{idalb}")
    public List<ChansonDTO> findByAlbum(@PathVariable Long idalb) {
        return chansonService.findByAlbumIdalb(idalb)
                .stream()
                .map(chansonService::convertEntityToDto)
                .toList();
    }
}