package com.chansons.chansons.controllers;

import com.chansons.chansons.entities.Album;
import com.chansons.chansons.entities.Chanson;
import com.chansons.chansons.repositories.AlbumRepository;
import com.chansons.chansons.services.ChansonService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChansonController {

    @Autowired
    ChansonService chansonService;

    @Autowired
    AlbumRepository albumRepository;

    @RequestMapping("/ListeChansons")
    public String listeChansons(ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {

        Page<Chanson> chs = chansonService.getAllChansonsParPage(page, size);
        modelMap.addAttribute("chansons", chs.getContent());
        modelMap.addAttribute("pages", new int[chs.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);
        return "listeChansons";
    }

    @RequestMapping("/showCreate")
    public String showCreate(ModelMap modelMap) {
        List<Album> albums = chansonService.getAllAlbums();
        modelMap.addAttribute("chanson", new Chanson());
        modelMap.addAttribute("mode", "new");
        modelMap.addAttribute("albums", albums);
        modelMap.addAttribute("page", 0);
        modelMap.addAttribute("size", 2);
        return "formChanson";
    }

    @RequestMapping("/saveChanson")
    public String saveChanson(
            @Valid @ModelAttribute("chanson") Chanson chanson,
            BindingResult bindingResult,
            @RequestParam(value = "album.idalb", required = false) Long idalb,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size,
            ModelMap modelMap) {

        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("albums", chansonService.getAllAlbums());
            modelMap.addAttribute("mode", chanson.getIdChanson() == null ? "new" : "edit");
            modelMap.addAttribute("page", page);
            modelMap.addAttribute("size", size);
            return "formChanson";
        }

        if (idalb != null) {
            chanson.setAlbum(albumRepository.findById(idalb).orElse(null));
        } else {
            chanson.setAlbum(null);
        }

        boolean isNew = (chanson.getIdChanson() == null);
        chansonService.saveChanson(chanson);

        int currentPage;
        if (isNew) {
            // Go to last page where new item was added
            Page<Chanson> chs = chansonService.getAllChansonsParPage(page, size);
            currentPage = chs.getTotalPages() - 1;
        } else {
            // ✅ Return to the same page the user was editing from
            currentPage = page;
        }

        return "redirect:/ListeChansons?page=" + currentPage + "&size=" + size;
    }

    @RequestMapping("/supprimerChanson")
    public String supprimerChanson(
            @RequestParam("id") Long id,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        chansonService.deleteChansonById(id);
        return "redirect:/ListeChansons?page=" + page + "&size=" + size;
    }

    @RequestMapping("/modifierChanson")
    public String editerChanson(
            @RequestParam("id") Long id,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size,
            ModelMap modelMap) {
        Chanson p = chansonService.getChanson(id);
        List<Album> albums = chansonService.getAllAlbums();
        modelMap.addAttribute("chanson", p);
        modelMap.addAttribute("albums", albums);
        modelMap.addAttribute("mode", "edit");
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("size", size);
        return "formChanson";
    }
    @RequestMapping("/rechercheChansons")
    public String rechercheChansons(
            @RequestParam(name = "mode", defaultValue = "artiste") String mode,
            ModelMap modelMap) {
        modelMap.addAttribute("chansons", null);
        modelMap.addAttribute("mode", mode);
        return "rechercheChansons";
    }

    @RequestMapping("/rechercherParArtiste")
    public String rechercherParArtiste(@RequestParam("artist") String artist, ModelMap modelMap) {
        List<Chanson> chansons = chansonService.findByArtistContains(artist);
        modelMap.addAttribute("chansons", chansons);
        modelMap.addAttribute("artist", artist);
        modelMap.addAttribute("mode", "artiste");
        return "rechercheChansons";
    }

    @RequestMapping("/rechercherParArtisteEtTitre")
    public String rechercherParArtisteEtTitre(
            @RequestParam("artist") String artist,
            @RequestParam("title") String title,
            ModelMap modelMap) {
        List<Chanson> chansons = chansonService.findByArtistContainsAndTitleContains(artist, title);
        modelMap.addAttribute("chansons", chansons);
        modelMap.addAttribute("artist", artist);
        modelMap.addAttribute("title", title);
        modelMap.addAttribute("mode", "artiste-titre");
        return "rechercheChansons";
    }
}