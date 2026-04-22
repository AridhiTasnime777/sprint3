import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { Chanson } from '../models/chanson.model';
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

  constructor(private activatedRoute: ActivatedRoute,
              private router: Router,
              private chansonService: ChansonService) { }

  ngOnInit(): void {
    let id = this.activatedRoute.snapshot.params['id'];
    const chanson = this.chansonService.consulterChanson(id);
    if(chanson) {
      this.currentChanson = chanson;
    }
  }

  updateChanson() {
    this.chansonService.updateChanson(this.currentChanson);
    this.router.navigate(['chansons']);
  }
}
