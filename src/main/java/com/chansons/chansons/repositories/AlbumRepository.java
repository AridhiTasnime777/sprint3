package com.chansons.chansons.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.chansons.chansons.entities.Album;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

}

