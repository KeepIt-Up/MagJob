import { LoginService } from './../login.service';
import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loginService = inject(LoginService);

  loginForm = new FormGroup({
    email: new FormControl(''),
    password: new FormControl('')
  });

  submitApplication() {
    this.loginService.submitApplication(
      this.loginForm.value.email ?? '',
      this.loginForm.value.password ?? ''
    );
  }
}
