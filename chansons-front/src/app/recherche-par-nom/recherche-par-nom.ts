import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Chanson } from '../models/chanson.model';
import { ChansonService } from '../services/chanson';

@Component({
  selector: 'app-recherche-par-nom',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './recherche-par-nom.html',
  styles: ``
})
export class RechercheParNomComponent implements OnInit {
  chansons: Chanson[] = [];
  allChansons: Chanson[] = [];
  searchTerm: string = '';

  constructor(private chansonService: ChansonService) {}

  ngOnInit(): void {
    this.chansonService.listeChansons().subscribe(data => {
      this.chansons = data;
      this.allChansons = data;
    });
  }

  rechercherChansons() {
    if (this.searchTerm) {
      this.chansonService.rechercherParNom(this.searchTerm).subscribe(data => {
        this.chansons = data;
      });
    } else {
      this.chansons = this.allChansons;
    }
  }

  onKeyUp(filterText: string) {
    this.chansons = this.allChansons.filter(c =>
      c.title.toLowerCase().includes(filterText.toLowerCase())
    );
  }
}
