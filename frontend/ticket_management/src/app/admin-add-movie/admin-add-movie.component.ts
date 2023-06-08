import { Component } from '@angular/core';
import { MovieService } from '../services/movie.service';
import { Movie } from '../models/movie.model';

@Component({
  selector: 'app-admin-add-movie',
  templateUrl: './admin-add-movie.component.html',
  styleUrls: ['./admin-add-movie.component.css']
})
export class AdminAddMovieComponent {
  newMovie: any = {};

  constructor(private movieService: MovieService) {}

  createMovie(): void {
    this.movieService.createMovie(this.newMovie).subscribe(
      (response) => {
        console.log('Movie created successfully:', response);
      },
      (error) => {
        console.log('Error creating movie:', error);
      }
    );
  }

}
