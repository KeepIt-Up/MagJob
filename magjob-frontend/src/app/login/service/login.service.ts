import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Login } from '../model/login'

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  readonly apiUrl = 'your_backend_api_url';

  constructor(private http: HttpClient) { }

  login(loginData: Login): Observable<any> {
   
    const loginEndpoint = `${this.apiUrl}/login`;
    

    return this.http.post(loginEndpoint, loginData);
  }
}