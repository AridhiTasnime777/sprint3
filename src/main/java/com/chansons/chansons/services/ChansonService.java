package com.chansons.chansons.services;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import com.chansons.chansons.entities.Album;
import com.chansons.chansons.entities.Chanson;
import com.chansons.chansons.dto.ChansonDTO;
public interface ChansonService {
	Page<Chanson> getAllChansonsParPage(int page, int size);
    Chanson saveChanson(Chanson c);
	Chanson updateChanson(Chanson c);
	void deleteChanson(Chanson c);
	void deleteChansonById(Long id);
	Chanson getChanson(Long id);
	List<Chanson> getAllChansons();
	List<Chanson> findByTitle(String title);
	List<Chanson> findByTitleContains(String title);
	List<Chanson> findByArtistContains(String artist);
	List<Chanson> findByArtistContainsAndTitleContains(String artist, String title);
	List<Chanson> findByTitleAndDateAfter(String title, Date date);
	List<Chanson> findByAlbum(Album album);
	List<Chanson> findByAlbumIdalb(Long idalb);
	List<Chanson> findByOrderByTitleAsc();
	List<Chanson> trierChansonsParTitreEtDate();
	List<Album> getAllAlbums();
	ChansonDTO convertEntityToDto (Chanson chanson);
	Chanson convertDtoToEntity(ChansonDTO dto);
	List<ChansonDTO> getAllChansonsDTO();
}
