import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MovieListComponent } from './movie-list/movie-list.component';
import { MovieItemComponent } from './movie-item/movie-item.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { NewTicketComponent } from './new-ticket/new-ticket.component';
import { ProfileComponent } from './profile/profile.component';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { AdminEditMovieComponent } from './admin-edit-movie/admin-edit-movie.component';
import { AdminAddMovieComponent } from './admin-add-movie/admin-add-movie.component';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: MovieListComponent },
  { path: 'movies/:id', component: MovieItemComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  { path: 'new-ticket/:movieId/:userId', component: NewTicketComponent },
  { path: 'users/:id', component: ProfileComponent },
  { path: 'admin', component: AdminHomeComponent },
  { path: 'admin-edit/:id', component: AdminEditMovieComponent },
  { path: 'admin-add', component: AdminAddMovieComponent },
];

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forRoot(routes),
    CommonModule
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }
