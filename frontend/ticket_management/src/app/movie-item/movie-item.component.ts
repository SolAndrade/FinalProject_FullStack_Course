import { Component, OnInit } from '@angular/core';
import { MovieService } from '../services/movie.service';
import { Movie } from '../models/movie.model';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../models/user.model';
import { UserService } from '../services/user.service';


@Component({
  selector: 'app-movie-item',
  templateUrl: './movie-item.component.html',
  styleUrls: ['./movie-item.component.css']
})
export class MovieItemComponent implements OnInit{
  loggedInUser: User | null = null;
  movie!: any;

  constructor(
    private movieService: MovieService,
    private activatedRoute: ActivatedRoute,
    private userService: UserService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loggedInUser = this.userService.getLoggedInUser();
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
  redirectToLogin(): void {
    this.router.navigate(['/login']); // Replace '/login' with the actual route for the login component
  }

  redirectToRegister(): void {
    this.router.navigate(['/register']); // Replace '/register' with the actual route for the register component
  }
}
