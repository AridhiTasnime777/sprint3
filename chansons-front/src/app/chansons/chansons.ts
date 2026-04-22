import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { Chanson } from '../models/chanson.model';
import { ChansonService } from '../services/chanson';

@Component({
  selector: 'app-chansons',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './chansons.html',
  styleUrl: './chansons.css'
})
export class Chansons implements OnInit {
    chansons?: Chanson[];

    constructor(private chansonService: ChansonService) { }

    ngOnInit(): void {
      this.chansons = this.chansonService.listeChansons();
    }

    supprimerChanson(c: Chanson) {
      let conf = confirm("Etes-vous sûr ?");
      if (conf)
        this.chansonService.supprimerChanson(c);
    }
}
