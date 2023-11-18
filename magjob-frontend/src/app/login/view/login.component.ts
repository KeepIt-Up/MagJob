import { LoginService } from '../service/login.service';
import { Component } from '@angular/core';
import { Login } from '../model/login';
import {FormControl, FormGroup, Validators} from '@angular/forms';


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

  constructor(private loginService: LoginService) {}

    submitApplication() {
      if (!(this.loginForm.invalid && (this.loginForm.dirty || this.loginForm.touched)) && this.loginModel.email && this.loginModel.password) {
        // Call the login service to verify the user's credentials
        this.loginService.login(this.loginModel).subscribe(
          (response) => {
            console.log('Login successful:', response);
            // Add any additional logic after a successful login
          },
          (error) => {
            console.error('Login failed:', error);
            // Handle login failure (show error message, etc.)
          }
        );
      }
  }
}