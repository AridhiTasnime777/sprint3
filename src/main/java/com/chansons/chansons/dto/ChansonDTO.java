package com.chansons.chansons.dto;

import java.util.Date;
import com.chansons.chansons.entities.Album;

public class ChansonDTO {

    private Long idChanson;
    private String title;
    private String artist;
    private Date releaseDate;
    private Album album;

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
}