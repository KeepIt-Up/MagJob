import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Register } from '../model/register';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  private apiUrl = 'your_backend_api_url'; // Replace with your actual backend API URL

  constructor(private http: HttpClient) {}

  register(RegisterData: Register): Observable<any> {
    const registrationEndpoint = `${this.apiUrl}/register`;

    return this.http.post(registrationEndpoint, RegisterData);
  }
}