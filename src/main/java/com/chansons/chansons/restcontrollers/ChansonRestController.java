package com.chansons.chansons.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.chansons.chansons.dto.ChansonDTO;
import com.chansons.chansons.entities.Chanson;
import com.chansons.chansons.services.ChansonServicelmpm;

@RestController
@RequestMapping("/api")
public class ChansonRestController {

    @Autowired
    ChansonServicelmpm chansonService;

    // 🔥 GET ALL DTO
    @GetMapping("/chansons")
    public List<ChansonDTO> getAllChansons() {
        return chansonService.getAllChansonsDTO();
    }

    // 🔥 GET BY ID
    @GetMapping("/chansons/{id}")
    public Chanson getChanson(@PathVariable Long id) {
        return chansonService.getChanson(id);
    }

    // 🔥 ADD
    @PostMapping("/chansons")
    public ChansonDTO createChanson(@RequestBody ChansonDTO dto) {
        Chanson ch = chansonService.convertDtoToEntity(dto);
        Chanson saved = chansonService.saveChanson(ch);
        return chansonService.convertEntityToDto(saved);
    }

    // 🔥 DELETE
    @DeleteMapping("/chansons/{id}")
    public void deleteChanson(@PathVariable Long id) {
        chansonService.deleteChansonById(id);
    }
}