package com.chansons.chansons.services;

import com.chansons.chansons.entities.Chanson;
import com.chansons.chansons.entities.Image;
import com.chansons.chansons.repositories.ChansonRepository;
import com.chansons.chansons.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    ChansonRepository chansonRepository;

    @Override
    public Image uploadImage(MultipartFile file) throws IOException {
        return imageRepository.save(new Image(file.getOriginalFilename(), file.getContentType(), file.getBytes()));
    }

    @Override
    public Image getImageDetails(Long id) throws IOException {
        final Optional<Image> dbImage = imageRepository.findById(id);
        if (dbImage.isEmpty()) return null;
        Image img = new Image(dbImage.get().getName(), dbImage.get().getType(), dbImage.get().getImage());
        img.setIdImage(dbImage.get().getIdImage());
        return img;
    }

    @Override
    public ResponseEntity<byte[]> getImage(Long id) throws IOException {
        final Optional<Image> dbImage = imageRepository.findById(id);
        if (dbImage.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(dbImage.get().getType()))
                .body(dbImage.get().getImage());
    }

    @Override
    public void deleteImage(Long id) {
        imageRepository.deleteById(id);
    }

    @Override
    public Image uploadImageProd(MultipartFile file, Long idChanson) throws IOException {
        Chanson c = new Chanson();
        c.setIdChanson(idChanson);
        return imageRepository.save(new Image(file.getOriginalFilename(), file.getContentType(), file.getBytes(), c));
    }

    @Override
    public List<Image> getImagesParChanson(Long chansonId) {
        Chanson c = chansonRepository.findById(chansonId).orElseThrow();
        return c.getImages();
    }
}
