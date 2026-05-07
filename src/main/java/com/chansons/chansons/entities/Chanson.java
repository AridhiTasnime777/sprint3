package com.chansons.chansons.entities;

import java.util.Date;
import java.util.List;
import jakarta.persistence.OneToMany;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Chanson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idChanson;

    @NotNull
    @Size(min = 2, max = 50)
    private String title;

    @NotNull
    @Size(min = 2, max = 50)
    private String artist;

    // ✅ FIX: @DateTimeFormat tells Spring how to parse the date from the HTML form
    // This removes the need for manual SimpleDateFormat parsing in the controller
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent
    private Date releaseDate;

    @ManyToOne
    private Album album;

    private String imagePath;

    @OneToMany(mappedBy = "chanson", cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true)
    private List<Image> images;

    public Chanson(String title, String artist, Date releaseDate) {
        this.title = title;
        this.artist = artist;
        this.releaseDate = releaseDate;
    }

    public Chanson() {}

    public Long getIdChanson() { return idChanson; }
    public void setIdChanson(Long idChanson) { this.idChanson = idChanson; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getArtist() { return artist; }
    public void setArtist(String artist) { this.artist = artist; }
    public Date getReleaseDate() { return releaseDate; }
    public void setReleaseDate(Date releaseDate) { this.releaseDate = releaseDate; }
    public Album getAlbum() { return album; }
    public void setAlbum(Album album) { this.album = album; }

    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
    public List<Image> getImages() { return images; }
    public void setImages(List<Image> images) { this.images = images; }

    @Override
    public String toString() {
        return "Chanson [idChanson=" + idChanson + ", title=" + title +
               ", artist=" + artist + ", releaseDate=" + releaseDate + "]";
    }
}