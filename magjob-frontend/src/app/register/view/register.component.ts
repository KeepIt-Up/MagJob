import { Component } from '@angular/core';
import {AbstractControl, FormControl, FormGroup, ValidationErrors, ValidatorFn, Validators} from '@angular/forms';
import { Register } from '../model/register'
import { RegisterService } from '../service/register.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  registerModel: Register = new Register('', '', '', ''); // Initialize with empty values

  passwordConfirmation: string = '';

  registerForm = new FormGroup({
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
      ]),
    passwordConfirmation: new FormControl(
      '',
      [
        this.passwordsMatchValidator(),
        Validators.required
      ]),
    firstname: new FormControl(
      '',
      [
        Validators.pattern('^[a-zA-Z0-9]*$'),
        Validators.required
      ]),
    surname: new FormControl(
      '',
      [
        Validators.pattern('^[a-zA-Z0-9]*$'),
        Validators.required
      ]),
  });

  constructor(private RegisterService: RegisterService, private router: Router) { }

  onSubmit() {
    if (!(this.registerForm.invalid && (this.registerForm.dirty || this.registerForm.touched))
    && this.registerModel.firstname && this.registerModel.lastname && this.registerModel.email && this.registerModel.password) {
      // Call the registration service to register the user
      this.RegisterService.register(this.registerModel).subscribe(
        (response) => {
          console.log('Registration successful:', response);
          localStorage.clear();
          this.router.navigate(['/login']);
          // Add any additional logic after a successful registration
        },
        (error) => {
          console.error('Registration failed:', error);
          // Handle registration failure (show error message, etc.)
        }
      );
    }
  }

  // TODO repair validator
  passwordsMatchValidator(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      return this.registerModel.password !== this.passwordConfirmation ? { doNotMatch: { value: control.value } } : null;
    }
  }
}
