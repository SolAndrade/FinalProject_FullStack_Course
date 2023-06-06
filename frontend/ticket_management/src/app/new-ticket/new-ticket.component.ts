import { Component, OnInit } from '@angular/core';
import { TicketService } from '../services/ticket.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Ticket } from '../models/ticket.model';
import { Movie } from '../models/movie.model';
import { MovieService } from '../services/movie.service';

@Component({
  selector: 'app-new-ticket',
  templateUrl: './new-ticket.component.html',
  styleUrls: ['./new-ticket.component.css']
})
export class NewTicketComponent implements OnInit {
  movieId!: number;
  movie!: Movie;
  ticket: Ticket = new Ticket(null, '', '');

  constructor(
    private movieService: MovieService,
    private ticketService: TicketService,
    private activatedRoute: ActivatedRoute,
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

  postTicket(): void {
    this.ticket.movie = this.movie;
    this.ticketService.postTicket(this.ticket).subscribe(
      (data) => {
        console.log('Ticket created successfully');
        //this.router.navigate(['/movies']);
      },
      (error) => {
        console.log('Error creating ticket: ' + error);
      }
    );
  }
}