import { Injectable } from '@angular/core';
import { Chanson } from '../models/chanson.model';

@Injectable({
  providedIn: 'root'
})
export class ChansonService {
  chansons: Chanson[]; 

  constructor() {
    this.chansons = [
      { idChanson: 1, title: "Bohemian Rhapsody", artist: "Queen", releaseDate: new Date("1975-10-31"), album: { idalb: 1, nomalb: "A Night at the Opera", descriptionalb: "Classic Album" } },
      { idChanson: 2, title: "Imagine", artist: "John Lennon", releaseDate: new Date("1971-09-09"), album: { idalb: 2, nomalb: "Imagine", descriptionalb: "Legendary Album" } },
      { idChanson: 3, title: "Hotel California", artist: "Eagles", releaseDate: new Date("1976-12-08"), album: { idalb: 3, nomalb: "Hotel California", descriptionalb: "Rock Masterpiece" } }
    ];
  }

  listeChansons(): Chanson[] {
    return this.chansons;
  }

  ajouterChanson( ch: Chanson){
    this.chansons.push(ch);
  }

  supprimerChanson( ch: Chanson){
    const index = this.chansons.indexOf(ch, 0);
    if (index > -1) {
      this.chansons.splice(index, 1);
    }
  }

  consulterChanson(id:number): Chanson | undefined {
    return this.chansons.find(c => c.idChanson == id);
  }

  updateChanson(ch: Chanson) {
    this.supprimerChanson(ch);
    this.ajouterChanson(ch);
    this.sortChansons();
  }

  sortChansons() {
    this.chansons.sort((x,y) => (x.idChanson > y.idChanson ? 1 : -1));
  }
}
