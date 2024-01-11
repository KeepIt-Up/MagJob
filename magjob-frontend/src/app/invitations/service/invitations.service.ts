import { Invitation } from './../model/invitation';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InvitationsService {
  readonly apiUrl = '/api/invitations';

  constructor(private http: HttpClient) {}

  getInvitations(userId: number): Observable<any>
  {
    return this.http.get<any>(`/api/users/${userId}/invitations`);
  }

  invite(invitation: Invitation) {
    return this.http.post(this.apiUrl, invitation)
      .pipe(
        catchError((error: any) => {
          // Handle error here
          console.error('Error occurred:', error);
          throw error; // You may want to throw the error or handle it in a different way
        })
      );
  }
}
