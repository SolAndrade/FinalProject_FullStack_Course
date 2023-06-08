import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';
import { catchError, map} from 'rxjs/operators';
import { throwError, of } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  private readonly API_URL = "http://localhost:8080/users";
  private loggedInUser: User | null = null;
  userList: any = [];

  constructor(private http: HttpClient) {
    
   }

   getUserById(userId: number): Observable<User> {
    const url = `${this.API_URL}/${userId}`;
    return this.http.get<User>(url);
  }

   getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.API_URL}/`)
   }
    
   createUser(body: any): Observable<any> {
    return this.http.post<any>(`${this.API_URL}/`, body)
   }

  login(credentials: { email: string, password: string }): Observable<any> {
    return this.http.get<User[]>(`${this.API_URL}/`).pipe(
      map(users => users.find(user => user.email === credentials.email && user.password === credentials.password))
    );
  }

  getUserByEmail(email: string): Observable<any> {
    alert(email)
    return this.http.get<User[]>(`${this.API_URL}/`).pipe(
      map(users => users.find(user => user.email === email))
    );
  }

   register(body: any): Observable<any> {
    return this.http.post<any>(`${this.API_URL}/`, body)
   }

  isAuthenticated(): boolean {
    return !!this.loggedInUser;
  }

  getLoggedInUser(): User | null {
    return this.loggedInUser;
  }

  setLoggedInUser(user: User | null): void {
    this.loggedInUser = user;
  }

}