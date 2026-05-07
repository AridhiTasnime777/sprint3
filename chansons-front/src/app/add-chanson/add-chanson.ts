import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { Chanson } from '../models/chanson.model';
import { Album } from '../models/album.model';
import { ChansonService } from '../services/chanson';

@Component({
  selector: 'app-add-chanson',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './add-chanson.html',
  styleUrl: './add-chanson.css'
})
export class AddChanson implements OnInit {
  newChanson = new Chanson();
  albums: Album[] = [];
  newIdAlb!: number;
  uploadedImage!: File;
  imagePath: any;

  constructor(private chansonService: ChansonService, private router: Router) { }

  ngOnInit(): void {
    this.chansonService.listeAlbums().subscribe(albs => {
      this.albums = albs;
    });
  }

  onImageUpload(event: any) {
    this.uploadedImage = event.target.files[0];
    var reader = new FileReader();
    reader.readAsDataURL(this.uploadedImage);
    reader.onload = (_event) => { this.imagePath = reader.result; }
  }

  addChanson() {
    this.chansonService.uploadImage(this.uploadedImage).subscribe((img: any) => {
      this.newChanson.image = img;
      this.newChanson.album = this.albums.find(a => a.idalb == this.newIdAlb)!;
      this.chansonService.ajouterChanson(this.newChanson).subscribe(ch => {
        console.log(ch);
        this.router.navigate(['chansons']);
      });
    });
  }
}

