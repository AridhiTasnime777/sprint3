package com.chansons.chansons.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.chansons.chansons.entities.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
