import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Login } from '../model/login'

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  readonly apiUrl = '/api/users';

  constructor(private http: HttpClient) { }

  login(loginData: Login): Observable<any> {

    const loginEndpoint = `${this.apiUrl}/login`;


    return this.http.post(loginEndpoint, loginData);
  }
}
