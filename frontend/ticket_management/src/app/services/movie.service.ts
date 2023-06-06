import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Movie } from '../models/movie.model';

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  private readonly API_URL = "http://localhost:8080/movies";

  movieList: any = [];

  constructor(private http: HttpClient) {
    
   }

   getAllMovies(): Observable<Movie[]> {
    return this.http.get<Movie[]>(`${this.API_URL}/`)
   }

   getMovieById(id: number): Observable<any> {
    return this.http.get(`${this.API_URL}/${id}`);
  }
  

}