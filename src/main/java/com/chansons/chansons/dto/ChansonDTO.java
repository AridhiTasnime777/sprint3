package com.chansons.chansons.dto;

import java.util.Date;
import com.chansons.chansons.entities.Album;
import com.chansons.chansons.entities.Image;
import java.util.List;

public class ChansonDTO {

    private Long idChanson;
    private String title;
    private String artist;
    private Date releaseDate;
    private Album album;
    private String imagePath;
    private Image image;
    private List<Image> images;

    // GETTERS
    public Long getIdChanson() {
        return idChanson;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public Album getAlbum() {
        return album;
    }
    
    public String getImagePath() {
        return imagePath;
    }

    public Image getImage() {
        return image;
    }

    public List<Image> getImages() {
        return images;
    }

    // SETTERS
    public void setIdChanson(Long idChanson) {
        this.idChanson = idChanson;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
    
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}