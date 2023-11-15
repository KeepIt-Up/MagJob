import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Register } from '../model/register'
import { RegisterService } from '../service/register.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  registerForm = new FormGroup({
    email: new FormControl(''),
    password: new FormControl(''),
    passwordConfirmation: new FormControl(''),
    firstname: new FormControl(''),
    surname: new FormControl('')
  });

  registerModel: Register = new Register('', '', '', ''); // Initialize with empty values

  constructor(private RegisterService: RegisterService) { }

  onSubmit() {
    // Call the registration service to register the user
    this.RegisterService.register(this.registerModel).subscribe(
      (response) => {
        console.log('Registration successful:', response);
        // Add any additional logic after a successful registration
      },
      (error) => {
        console.error('Registration failed:', error);
        // Handle registration failure (show error message, etc.)
      }
    );
  }
}
