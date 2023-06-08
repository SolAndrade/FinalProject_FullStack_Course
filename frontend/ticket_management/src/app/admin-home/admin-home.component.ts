import { Component } from '@angular/core';
import { MovieService } from '../services/movie.service';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.css']
})
export class AdminHomeComponent {
  movieList: any;

  constructor(
    private movieService: MovieService, 
    private router: Router,
    private userService: UserService
  ) { }

  ngOnInit(): void {
    this.getAllMovies();
  }

  getAllMovies(): void {
    this.movieService.getAllMovies().subscribe(
      {
        next: (data) => {
          console.log(data);
          this.movieList = data;
        }
      }
    )
  }

  addMovie(): void{
    this.router.navigate(['/admin-add']);
  }
}
