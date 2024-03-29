import { Component, OnInit } from '@angular/core';
import { MovieService } from '../services/movie.service';
import { Movie } from '../models/movie.model';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../models/user.model';
import { UserService } from '../services/user.service';
import { Ticket } from '../models/ticket.model';
import { TicketService } from '../services/ticket.service';


@Component({
  selector: 'app-movie-item',
  templateUrl: './movie-item.component.html',
  styleUrls: ['./movie-item.component.css']
})
export class MovieItemComponent implements OnInit{
  loggedInUser: User | null = null;
  movie!: any;
  ticket!: any;

  constructor(
    private movieService: MovieService,
    private activatedRoute: ActivatedRoute,
    private userService: UserService,
    private ticketService: TicketService,
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
    this.router.navigate(['/login']);
  }

  redirectToRegister(): void {
    this.router.navigate(['/register']); 
  }

  redirectToProfile(): void {
    const loggedInUserId = this.loggedInUser!.id;
    if (loggedInUserId) {
      this.router.navigate(['/users', loggedInUserId]);
    }
    else {
      alert('error getting the user');
    }
  }  

  redirectToBuyTicket(): void {
    if (!this.loggedInUser) {
      this.router.navigate(['/login']); 
    } else {
      const id = this.activatedRoute.snapshot.params["id"];
      this.ticket.movie = this.movieService.getMovieById(id);
      this.ticket.user = this.loggedInUser;
      this.ticketService.createTicket(this.ticket);
      // this.router.navigate(['/new-ticket', this.movie.id, this.loggedInUser.id]);
    }
  }
}
