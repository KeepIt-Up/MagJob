import { Organization } from './../../organization/model/organization';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../model/user';
import { OrganizationService } from 'src/app/organization/service/organization.service';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private currentUserId: number | null = null;
  organizations: Organization[] = [];
  private apiUrl = '/api/users';

  constructor(private organizationService: OrganizationService,
    private http: HttpClient)
  {

  }

  setCurrentUserId(user: User): void {
    this.currentUserId = user.id;
    localStorage.setItem("User",this.currentUserId.toString());
  }

  getCurrentUserId(): number | null {
    if(this.currentUserId == null)
      this.currentUserId =  parseInt(localStorage.getItem("User") || '0')
    return this.currentUserId;
  }

  clearCurrentUser(): void {
    this.currentUserId = null;
  }

  belongToAnyOrganization(): boolean
  {
    const userId: number = parseInt(localStorage.getItem("User") || '0', 10);
    this.organizationService.getUserOrganizations(userId).subscribe(
      (organizations: Organization[]) => {
        this.organizations = organizations;
        //console.log(this.organizations);
      },
      (error) => {
        console.error('Error fetching organizations:', error);
      }
    );
    if(this.organizations.length == 0)
    {
      return false;
    }
    else{
      return true;
    }
  }

  getUserData(userId: number): Observable<any>
  {
    const url = `${this.apiUrl}/${userId}`;
    return this.http.get(url);
  }

  updateUserData(userId: number, userData: User): Observable<any>
  {
    const url = `${this.apiUrl}/${userId}`;
    return this.http.patch(url, userData);
  }
}
