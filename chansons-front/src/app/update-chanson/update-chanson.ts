import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { Chanson } from '../models/chanson.model';
import { Album } from '../models/album.model';
import { ChansonService } from '../services/chanson';

@Component({
  selector: 'app-update-chanson',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './update-chanson.html',
  styleUrl: './update-chanson.css'
})
export class UpdateChanson implements OnInit {
  currentChanson = new Chanson();
  albums: Album[] = [];
  updatedIdAlb!: number;
  debugInfo: string = "Init...";
  errorMessage: string = "";

  constructor(private activatedRoute: ActivatedRoute,
              private router: Router,
              private chansonService: ChansonService,
              private cdr: ChangeDetectorRef) { }

  ngOnInit(): void {
    this.chansonService.listeAlbums().subscribe(albs => {
      this.albums = albs;
    });
    let id = this.activatedRoute.snapshot.params['id'];
    this.debugInfo = "Fetching ID: " + id;
    
    this.chansonService.consulterChanson(id).subscribe({
      next: (ch) => {
        this.debugInfo += " | Success! Title: " + ch.title;
        this.currentChanson = ch;
        this.updatedIdAlb = ch.album?.idalb!;
        this.cdr.detectChanges(); // FORCES Angular to update the view immediately
      },
      error: (err) => {
        this.errorMessage = "Error API: " + err.message + " | Status: " + err.status;
        console.error("Error fetching chanson:", err);
      }
    });
  }

  updateChanson() {
    this.currentChanson.album = this.albums.find(a => a.idalb == this.updatedIdAlb)!;
    this.chansonService.updateChanson(this.currentChanson).subscribe({
      next: (ch) => {
        this.router.navigate(['chansons']);
      },
      error: (err) => {
        console.error("Error updating chanson:", err);
        alert("Erreur lors de la modification. Regardez la console (F12).");
      }
    });
  }
}

