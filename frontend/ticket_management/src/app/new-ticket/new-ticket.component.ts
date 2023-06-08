import { Component, OnInit } from '@angular/core';
import { TicketService } from '../services/ticket.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Ticket } from '../models/ticket.model';
import { Movie } from '../models/movie.model';
import { MovieService } from '../services/movie.service';
import { User } from '../models/user.model';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-new-ticket',
  templateUrl: './new-ticket.component.html',
  styleUrls: ['./new-ticket.component.css']
})
export class NewTicketComponent implements OnInit {
  movieId!: number;
  movie!: Movie;
  ticket!: Ticket;
  email!: '';
  errorMessage: string = '';

  constructor(
    private movieService: MovieService,
    private ticketService: TicketService,
    private activatedRoute: ActivatedRoute,
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.movieId = +this.activatedRoute.snapshot.params['id'];
    this.getMovie();
  }

  getMovie(): void {
    this.movieService.getMovieById(this.movieId).subscribe(
      (data) => {
        this.movie = data;
      },
      (error) => {
        console.log(error);
      }
    );
  }

  onSubmit(): void {
    if (!this.email) {
      return;
    }

    if (!this.validateEmail(this.email)) {
      return;
    }

    this.userService.getUserByEmail(this.email).subscribe(
      (user: User) => {
        alert(user.email)
        if (user) {
          this.ticket.movie = this.movie;
          this.ticket.user = user;
            // userName: this.ticket.user_name,
            // userEmail: this.ticket.user_email
          this.ticketService.createTicket(this.ticket).subscribe(
            (response) => {
              console.log('Ticket created successfully:', response);
            },
            (error) => {
              console.log('Error creating ticket:', error);
            }
          );
          // this.userService.setLoggedInUser(user);
          // this.router.navigate(['/users', user.id]);
        } else {
          this.errorMessage = 'User doesnt exist. Please register.';
        }
      },
      (error) => {
        this.errorMessage = 'User doesnt exist. Please register.';
        console.log('Error logging in:', error);
      }
    )
    // alert(this.ticket.user_name);
    
  }
  validateEmail(email: string): boolean {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
  }
}
