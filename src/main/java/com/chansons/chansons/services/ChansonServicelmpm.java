package com.chansons.chansons.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.chansons.chansons.entities.Album;
import com.chansons.chansons.entities.Chanson;
import com.chansons.chansons.repositories.AlbumRepository;
import com.chansons.chansons.repositories.ChansonRepository;
import com.chansons.chansons.dto.ChansonDTO;

@Service
public class ChansonServicelmpm implements ChansonService {

    @Autowired
    ChansonRepository chansonRepository;

    @Autowired
    AlbumRepository albumRepository;

    @Override
    public Page<Chanson> getAllChansonsParPage(int page, int size) {
        return chansonRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Chanson saveChanson(Chanson c) {
        return chansonRepository.save(c);
    }

    @Override
    public Chanson updateChanson(Chanson c) {
        return chansonRepository.save(c);
    }

    @Override
    public void deleteChanson(Chanson c) {
        chansonRepository.delete(c);
    }

    @Override
    public void deleteChansonById(Long id) {
        chansonRepository.deleteById(id);
    }

    @Override
    public Chanson getChanson(Long id) {
        return chansonRepository.findById(id).orElse(null);
    }

    @Override
    public List<Chanson> getAllChansons() {
        return chansonRepository.findAll();
    }

    @Override
    public List<Chanson> findByTitle(String title) {
        return chansonRepository.findByTitle(title);
    }

    @Override
    public List<Chanson> findByTitleContains(String title) {
        return chansonRepository.findByTitleContains(title);
    }

    @Override
    public List<Chanson> findByTitleAndDateAfter(String title, Date date) {
        return chansonRepository.findByTitleAndDateAfter(title, date);
    }

    @Override
    public List<Chanson> findByAlbum(Album album) {
        return chansonRepository.findByAlbum(album);
    }

    @Override
    public List<Chanson> findByAlbumIdalb(Long idalb) {
        return chansonRepository.findByAlbumIdalb(idalb);
    }

    @Override
    public List<Chanson> findByOrderByTitleAsc() {
        return chansonRepository.findByOrderByTitleAsc();
    }

    @Override
    public List<Chanson> trierChansonsParTitreEtDate() {
        return chansonRepository.trierChansonsParTitreEtDate();
    }

    @Override
    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    // 🔥 DTO
    @Override
    public ChansonDTO convertEntityToDto(Chanson ch) {
        ChansonDTO dto = new ChansonDTO();
        dto.setIdChanson(ch.getIdChanson());
        dto.setTitle(ch.getTitle());
        dto.setArtist(ch.getArtist());
        dto.setReleaseDate(ch.getReleaseDate());
        dto.setAlbum(ch.getAlbum());
        return dto;
    }

    public Chanson convertDtoToEntity(ChansonDTO dto) {
        Chanson ch = new Chanson();
        ch.setIdChanson(dto.getIdChanson());
        ch.setTitle(dto.getTitle());
        ch.setArtist(dto.getArtist());
        ch.setReleaseDate(dto.getReleaseDate());
        ch.setAlbum(dto.getAlbum());
        return ch;
    }

    public List<ChansonDTO> getAllChansonsDTO() {
        return chansonRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }
}