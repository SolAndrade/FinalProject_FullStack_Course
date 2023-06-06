import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MovieService } from '../services/movie.service';
import { Router } from '@angular/router';
import { Movie } from '../models/movie.model';
import { UserService } from '../services/user.service';
import { User } from '../models/user.model';

@Component({
  selector: 'app-movie-list',
  templateUrl: './movie-list.component.html',
  styleUrls: ['./movie-list.component.css']
})
export class MovieListComponent implements OnInit {
  loggedInUser: User | null = null;
  movieList: any;

  constructor(
    private movieService: MovieService, 
    private router: Router,
    private userService: UserService
  ) { }

  ngOnInit(): void {
    this.getAllMovies();
    this.loggedInUser = this.userService.getLoggedInUser();
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

  redirectToLogin(): void {
    this.router.navigate(['/login']); // Replace '/login' with the actual route for the login component
  }

  redirectToRegister(): void {
    this.router.navigate(['/register']); // Replace '/register' with the actual route for the register component
  }
}
