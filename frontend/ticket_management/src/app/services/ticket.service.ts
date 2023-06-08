import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Ticket } from '../models/ticket.model'
import { Observable } from 'rxjs';
import { catchError, map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TicketService {
  private readonly API_URL = 'http://localhost:8080/tickets';

  constructor(private http: HttpClient) {}

  createTicket(ticket: any): Observable<any> {
    return this.http.post<Ticket>(this.API_URL, ticket);
  }

  getAllTickets(): Observable<Ticket[]> {
    return this.http.get<Ticket[]>(`${this.API_URL}/tickets`);
  }

  getTicketsByUserId(userId: number): Observable<Ticket[]> {
    return this.getAllTickets().pipe(
      map((tickets: Ticket[]) => tickets.filter(ticket => ticket.user?.id === userId))
    );
  }
}