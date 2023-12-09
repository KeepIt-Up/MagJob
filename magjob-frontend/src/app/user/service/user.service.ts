import { Organization } from './../../organization/model/organization';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserModel } from '../model/user';
import { OrganizationService } from 'src/app/organization/service/organization.service';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private currentUser: UserModel | null = null;
  organizations: Organization[] = [];
  private apiUrl = '/api/users';

  constructor(private organizationService: OrganizationService,
    private http: HttpClient)
  {

  }

  setCurrentUser(user: UserModel): void {
    this.currentUser = user;
    localStorage.setItem("User",this.currentUser.id.toString());
  }

  getCurrentUser(): UserModel | null {
    return this.currentUser;
  }

  clearCurrentUser(): void {
    this.currentUser = null;
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

  updateUserData(userId: number, userData: UserModel): Observable<any>
  {
    const url = `${this.apiUrl}/${userId}`;
    return this.http.patch(url, userData);
  }
}
