import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Invitation } from '../model/invitation';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class InvitationsService {
  readonly apiUrl = '/api/users/invitations';

  constructor(private http: HttpClient) {}

  getInvitations(userId: number): Observable<Invitation[]>
  {
    return this.http.get<Invitation[]>(`${this.apiUrl}/${userId}`);
  }
}
