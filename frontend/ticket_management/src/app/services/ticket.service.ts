import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Ticket } from '../models/ticket.model'
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TicketService {
  private readonly API_URL = 'http://localhost:8080/tickets';

  constructor(private http: HttpClient) {}

  postTicket(ticket: Ticket): Observable<any> {
    return this.http.post(this.API_URL, ticket);
  }
}