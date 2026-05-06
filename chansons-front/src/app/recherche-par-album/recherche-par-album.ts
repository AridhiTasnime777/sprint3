import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Chanson } from '../models/chanson.model';
import { Album } from '../models/album.model';
import { ChansonService } from '../services/chanson';

@Component({
  selector: 'app-recherche-par-album',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './recherche-par-album.html',
  styles: ``
})
export class RechercheParAlbumComponent implements OnInit {
  produits: Chanson[] = [];
  IdAlbum!: number;
  albums: Album[] = [];

  constructor(private chansonService: ChansonService) {}

  ngOnInit(): void {
    this.chansonService.listeAlbums().subscribe(albs => {
      this.albums = albs;
    });
  }

  onChange() {
    this.chansonService.rechercherParAlbum(this.IdAlbum).subscribe(chansons => {
      this.produits = chansons;
    });
  }
}
