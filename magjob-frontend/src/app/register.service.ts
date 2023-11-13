import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  readonly baseUrl = 'https://angular.io/assets/images/tutorials/faa';

  constructor() { }

  submitApplication(email: string, password: string, passwordConfirmation: string, fisrtname: string, surname: string) {
    //TODO
  }
}
