import { Component } from '@angular/core';
import { User } from '../models/user.model'
import { UserService } from '../services/user.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  email: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(
    private userService: UserService,
    private router: Router
  ) {}

  login(): void {
    this.userService.login(this.email, this.password).subscribe(
      (response) => {
        // Login successful
        this.userService.setLoggedInUser(response); // Assuming the user object is returned directly in the response
        this.router.navigate(['/home']);
        alert('Logged in');
      },
      (error) => {
        // Login failed
        this.errorMessage = 'Invalid email or password. Please try again.';
      }
    );
  }
}