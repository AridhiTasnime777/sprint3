package com.chansons.chansons.repositories;

import com.chansons.chansons.entities.Chanson;
import com.chansons.chansons.entities.Album;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

@Repository
public interface ChansonRepository extends JpaRepository<Chanson, Long> {

    // Find by exact title
    List<Chanson> findByTitle(String title);

    // Find by title containing a keyword
    List<Chanson> findByTitleContains(String title);

    // Find by artist containing a keyword
    List<Chanson> findByArtistContains(String artist);

    // Find by both artist and title containing keywords
    List<Chanson> findByArtistContainsAndTitleContains(String artist, String title);

    // Custom query: chansons whose title ends with 'nom' and released after a date
    @Query("select c from Chanson c where c.title like %:nom and c.releaseDate > :date")
    List<Chanson> findByTitleAndDateAfter(@Param("nom") String nom, @Param("date") Date date);

    // Find by album object
    List<Chanson> findByAlbum(Album album);

    // Find by album id
    List<Chanson> findByAlbumIdalb(Long idalb);

    // Find all ordered by title ascending
    List<Chanson> findByOrderByTitleAsc();

    // Find all ordered by title ASC and releaseDate DESC
    @Query("select c from Chanson c order by c.title ASC, c.releaseDate DESC")
    List<Chanson> trierChansonsParTitreEtDate();
}