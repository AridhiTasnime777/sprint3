package com.chansons.chansons.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Builder
@Data
@AllArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idImage;
    private String name;
    private String type;

    @Column(name = "IMAGE", length = 4048576)
    @Lob
    private byte[] image;

    @ManyToOne()
    @JoinColumn(name = "CHANSON_ID")
    @JsonIgnore
    private Chanson chanson;

    public Image() {}

    public Image(String name, String type, byte[] image) {
        this.name = name;
        this.type = type;
        this.image = image;
    }

    public Image(String name, String type, byte[] image, Chanson chanson) {
        this.name = name;
        this.type = type;
        this.image = image;
        this.chanson = chanson;
    }

    public Long getIdImage() { return idImage; }
    public void setIdImage(Long idImage) { this.idImage = idImage; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public byte[] getImage() { return image; }
    public void setImage(byte[] image) { this.image = image; }
    public Chanson getChanson() { return chanson; }
    public void setChanson(Chanson chanson) { this.chanson = chanson; }
}
