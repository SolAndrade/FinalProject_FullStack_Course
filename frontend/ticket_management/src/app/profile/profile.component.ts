import { Component } from '@angular/core';
import { User } from '../models/user.model';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';
import { TicketService } from '../services/ticket.service';
import { Ticket } from '../models/ticket.model';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {
  tickets: Ticket[] = [];
  userId!: number;
  user!: User;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private userService: UserService,
    private ticketService: TicketService
    ) { }
    
    ngOnInit(): void {
      this.userId = +this.route.snapshot.params['id'];
      this.getUser();
      this.getTickets();
    }
  
    getUser(): void {
      this.userService.getUserById(this.userId).subscribe(
        (user) => {
          this.user = user;
        },
        (error) => {
          console.log('Error retrieving user:', error);
        }
      );
    }

    getTickets(): void {
      this.ticketService.getTicketsByUserId(this.userId).subscribe(
        (tickets: Ticket[]) => {
          this.tickets = tickets;
        },
        (error) => {
          console.log('Error retrieving tickets:', error);
        }
      );  
    }
  

  logout(): void {
    this.userService.setLoggedInUser(null);
    this.router.navigate(['/login']);
  }

  redirectToHome(): void {
    this.router.navigate(['/home']);
  }
}
