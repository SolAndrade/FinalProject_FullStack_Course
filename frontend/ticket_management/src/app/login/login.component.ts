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
    if (!this.email) {
      return;
    }

    if (!this.validateEmail(this.email)) {
      return;
    }

    if (!this.password) {
      return;
    }
    const credentials = {
      email: this.email,
      password: this.password
    };
  
    this.userService.login(credentials).subscribe(
      (loggedInUser: User) => {
        if (loggedInUser) {
          this.userService.setLoggedInUser(loggedInUser);
          // this.router.navigate(['/users', loggedInUser.id]);
          this.router.navigate(['/home']);
        } else {
          this.errorMessage = 'Login failed. Please check your email and password.';
        }
      },
      (error) => {
        console.log('Error logging in:', error);
      }
    );
  }

  validateEmail(email: string): boolean {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
  }
}