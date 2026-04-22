import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { Chanson } from '../models/chanson.model';
import { ChansonService } from '../services/chanson';

@Component({
  selector: 'app-add-chanson',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './add-chanson.html',
  styleUrl: './add-chanson.css'
})
export class AddChanson implements OnInit {
  newChanson = new Chanson();

  constructor(private chansonService: ChansonService, private router: Router) { }

  ngOnInit(): void {
  }

  addChanson(){
    this.chansonService.ajouterChanson(this.newChanson);
    this.router.navigate(['chansons']);
  }
}
