import { Injectable } from '@angular/core';
import { LoginComponent } from './login/login.component';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  readonly baseUrl = 'https://angular.io/assets/images/tutorials/faa';

  constructor() { }

  submitApplication(email: string, password: string) {
    //TODO
  }
}
