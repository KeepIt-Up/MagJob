import { Organization } from './../../organization/model/organization';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { UserModel } from '../model/user';
import { OrganizationService } from 'src/app/organization/service/organization.service';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private currentUser: UserModel | null = null;
  organizations: Organization[] = [];

  constructor(private organizationService: OrganizationService)
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
        console.log(this.organizations);
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
}
