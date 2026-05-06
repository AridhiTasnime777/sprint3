import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { Chanson } from '../models/chanson.model';
import { ChansonService } from '../services/chanson';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-chansons',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './chansons.html',
  styleUrl: './chansons.css'
})
export class Chansons implements OnInit {
    chansons: Chanson[] = [];
    errorMessage: string = "";

    constructor(private chansonService: ChansonService, 
                public authService: AuthService,
                private cdr: ChangeDetectorRef) { }

    ngOnInit(): void {
      this.chargerChansons();
    }

    chargerChansons() {
      this.chansonService.listeChansons().subscribe(data => {
        this.chansons = data;
        this.cdr.detectChanges();
      });
    }

    supprimerChanson(c: Chanson) {
      let conf = confirm("Etes-vous sûr ?");
      if (conf)
      this.chansonService.supprimerChanson(c.idChanson).subscribe({
        next: () => {
          console.log("Deleted successfully");
          this.chargerChansons();
          this.cdr.detectChanges();
        },
        error: (err) => {
          this.errorMessage = "Error API Delete: " + err.message + " | Status: " + err.status;
          console.error("Error during deletion:", err);
        }
      });
    }
}

