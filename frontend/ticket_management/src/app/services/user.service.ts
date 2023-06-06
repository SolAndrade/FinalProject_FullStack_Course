import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private readonly API_URL = "http://localhost:8080/users";
  private loggedInUser: User | null = null;
  userList: any = [];

  constructor(private http: HttpClient) {
    
   }

   getUserById(id: number): Observable<any> {
    return this.http.get(`${this.API_URL}/${id}`)
   }

   register(body: any): Observable<any> {
    return this.http.post<any>(`${this.API_URL}/`, body)
   }

   login(email: string, password: string): Observable<any> {
    const body = { email, password };
    return this.http.post(`${this.API_URL}/login`, body);
  }

  isAuthenticated(): boolean {
    return !!this.loggedInUser;
  }

  getLoggedInUser(): User | null {
    return this.loggedInUser;
  }

  setLoggedInUser(user: User): void {
    this.loggedInUser = user;
  }

}