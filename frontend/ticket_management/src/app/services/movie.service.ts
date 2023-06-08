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

  updateMovie(id: number, movie: Movie): Observable<Movie> {
    const url = `${this.API_URL}/${id}`;
    return this.http.put<Movie>(url, movie);
  }

  deleteMovie(id: number): Observable<void> {
    const url = `${this.API_URL}/${id}`;
    return this.http.delete<void>(url);
  }

  createMovie(movie: any): Observable<any> {
    return this.http.post<any>(this.API_URL, movie);
  }
}