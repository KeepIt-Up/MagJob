import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { RegisterService } from '../register.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  registerService = inject(RegisterService);

  registerForm = new FormGroup({
    email: new FormControl(''),
    password: new FormControl(''),
    passwordConfirmation: new FormControl(''),
    firstname: new FormControl(''),
    surname: new FormControl('')
  });

  submitApplication() {
    this.registerService.submitApplication(
      this.registerForm.value.email ?? '',
      this.registerForm.value.password ?? '',
      this.registerForm.value.passwordConfirmation ?? '',
      this.registerForm.value.firstname ?? '',
      this.registerForm.value.surname ?? ''
    );
  }
}
