import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Invitation } from '../model/invitation';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class InvitationsService {
  readonly apiUrl = '/api/users';

  constructor(private http: HttpClient) {}

  getInvitations(userId: number): Observable<any>
  {
    return this.http.get<any>(`${this.apiUrl}/${userId}/invitations`);
  }
}
