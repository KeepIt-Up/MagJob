import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { Organization } from "../../organization/model/organization";
import { OrganizationService } from "../../organization/service/organization.service";
import { UserService } from "../../user/service/user.service";

@Component({
  selector: 'app-select-bar',
  templateUrl: './select-bar.component.html',
  styleUrls: ['./select-bar.component.css']
})
export class SelectBarComponent implements OnInit {
  organizations: Organization[] = [];
  selectedOrganization: number | string = '';
  userId: number | null = null;

  constructor(
    private organizationService: OrganizationService,
    private userService: UserService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.loadUserOrganizations();
  }

  loadUserOrganizations(): void {
    this.userId = this.userService.getCurrentUserId();

    if (this.userId != null) {
      this.organizationService.getUserOrganizations(this.userId).subscribe(
        (data: { organizations: Organization[] }) => {
          if (Array.isArray(data.organizations)) {
            this.organizations = data.organizations;
          } else {
            console.error('Invalid data format. Expected an array, but received:', data.organizations);
            this.organizations = [];
          }
        },
        (error) => {
          console.error('Error while getting organizations:', error);
          this.organizations = [];
        }
      );
    }
  }

  handleChange(): void {
    if (this.selectedOrganization === 'nonOrganization') {
      this.router.navigate(['/create-organization']);
    } else if (this.selectedOrganization === 'addOrganization') {
      this.router.navigate(['/create-organization']);
    } else {
      this.router.navigate(['/organization', this.selectedOrganization]);
    }
  }
}
