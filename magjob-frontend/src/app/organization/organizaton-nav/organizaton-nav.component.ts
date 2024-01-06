import { Component } from '@angular/core';
import { UserService } from './../../user/service/user.service';
import { AuthService } from './../../jwt/auth.service';
import { OrganizationService } from '../service/organization.service';

@Component({
  selector: 'app-organizaton-nav',
  templateUrl: './organizaton-nav.component.html',
  styleUrls: ['./organizaton-nav.component.css']
})
export class OrganizatonNavComponent {

  constructor(private authService: AuthService, private organizationService: OrganizationService) {}

  getCurrentOrganizationId(): number | null {
    const currentOrganization = this.organizationService.getCurrentOrganization();
    return currentOrganization ? currentOrganization.id : null;
  }
}
