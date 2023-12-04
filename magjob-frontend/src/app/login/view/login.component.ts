import { AuthService } from '../../jwt/auth.service';
import { Component } from '@angular/core';
import { Login } from '../model/login';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loginForm = new FormGroup({
    email: new FormControl(
      '',
      [
        Validators.email,
        Validators.required
      ]),
    password: new FormControl(
      '',
      [
        Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{5,}$'),
        Validators.required
      ])
  });

  loginModel: Login = new Login('', ''); // Initialize with empty values

  constructor(private authService: AuthService, private router: Router) {}

    submitApplication() {
      if (!(this.loginForm.invalid && (this.loginForm.dirty || this.loginForm.touched)) && this.loginModel.email && this.loginModel.password) {
        this.authService.login(this.loginModel).subscribe(
          (response) => {
            localStorage.setItem('access_token', response.jwt);
            const userId = response.user.id;
            this.router.navigate(['/user', userId]);
          },
          (error) => {
            console.error('Login failed:', error);
          }
        );
      }
  }
}
