import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user.model';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  newUser: any = {};

  constructor( 
    private router: Router,
    private userService: UserService
  ) {}

  onSubmit() {
    this.userService.register(this.newUser).subscribe(
      (response) => {
        console.log('Beer created successfully:', response);
        this.router.navigate(['/']);
      },
      (error) => {
        console.log('Error creating beer:', error);
        alert('Error creating beer: ' + error.message);
      }
    );
  }
}


