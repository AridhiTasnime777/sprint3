import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Chanson } from '../models/chanson.model';
import { Album } from '../models/album.model';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class ChansonService {
  apiURL: string = 'http://localhost:8081/chansons/chansons/api';

  constructor(private http: HttpClient) {}

  listeChansons(): Observable<Chanson[]> {
    return this.http.get<Chanson[]>(this.apiURL + '/chansons');
  }

  ajouterChanson(ch: Chanson): Observable<Chanson> {
    return this.http.post<Chanson>(this.apiURL + '/chansons', ch, httpOptions);
  }

  supprimerChanson(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiURL}/chansons/${id}`, httpOptions);
  }

  consulterChanson(id: number): Observable<Chanson> {
    return this.http.get<Chanson>(`${this.apiURL}/chansons/${id}`);
  }

  updateChanson(ch: Chanson): Observable<Chanson> {
    return this.http.put<Chanson>(this.apiURL + '/chansons', ch, httpOptions);
  }

  listeAlbums(): Observable<Album[]> {
    return this.http.get<Album[]>(this.apiURL + '/alb');
  }

  rechercherParNom(nom: string): Observable<Chanson[]> {
    return this.http.get<Chanson[]>(`${this.apiURL}/chansons/byName/${nom}`);
  }

  rechercherParAlbum(idalb: number): Observable<Chanson[]> {
    return this.http.get<Chanson[]>(`${this.apiURL}/chansons/byAlbum/${idalb}`);
  }
}
