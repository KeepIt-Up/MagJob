import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Organization } from '../model/organization';
import { Member } from '../model/member';

@Injectable({
  providedIn: 'root'
})
export class OrganizationService {

  private apiUrl = '/api/organizations';
  constructor(private http: HttpClient) { }

  getAllOrganizations(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}`);
  }

  getUserOrganizations(userId: number): Observable<Organization[]> {
    return this.http.get<Organization[]>(`${this.apiUrl}/users/${userId}`);
  }

  getMembers(organizationId: number): Observable<Member[]>
  {
    //TODO connect with endpoint
    return this.http.get<Member[]>(`${this.apiUrl}/${organizationId}/members`);
  }
}
