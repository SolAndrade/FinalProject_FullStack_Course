import { Component } from '@angular/core';
import { MovieService } from '../services/movie.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Movie } from '../models/movie.model';

@Component({
  selector: 'app-admin-edit-movie',
  templateUrl: './admin-edit-movie.component.html',
  styleUrls: ['./admin-edit-movie.component.css']
})
export class AdminEditMovieComponent {
  movie!: any;

  constructor(
    private movieService: MovieService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    console.log(this.activatedRoute.snapshot.params["id"]);
    const id = this.activatedRoute.snapshot.params["id"];

    if (id) {
      this.movieService.getMovieById(id).subscribe(
        {
          next: (data) => {
            console.log(data);
            this.movie = data;       
          },
          error: (e) => {
            console.log(e);
            alert('Error finding movie: ' + e.message);
          }
        }
      )
    }
  }

  editMovie(): void {
    this.movieService.updateMovie(this.movie.id, this.movie).subscribe(
      (updatedMovie: Movie) => {
        console.log('Movie updated successfully:', updatedMovie);
      },
      (error) => {
        console.log('Error updating movie:', error);
      }
    );
  }

  deleteMovie(): void {
    if (confirm('Are you sure you want to delete this movie?')) {
      this.movieService.deleteMovie(this.movie.id).subscribe(
        () => {
          console.log('Movie deleted successfully');
          this.router.navigate(['/movies']); 
        },
        (error) => {
          console.log('Error deleting movie:', error);
        }
      );
    }
  }

  addMovie(): void{
    this.router.navigate(['/admin-add']);
  }
}
