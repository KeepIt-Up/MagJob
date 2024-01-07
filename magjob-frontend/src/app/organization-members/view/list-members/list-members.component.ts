import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { OrganizationService } from '../../../organization/service/organization.service';

@Component({
  selector: 'app-list-members',
  templateUrl: './list-members.component.html',
  styleUrls: ['./list-members.component.css']
})
export class ListMembersComponent implements OnInit {
  members: any[] = [];
  organizationId: number | null = null;

  constructor(private route: ActivatedRoute, private organizationService: OrganizationService) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.organizationId = this.organizationService.getCurrentOrganizationId();
      if (this.organizationId) {
        console.log("Damian")
        this.loadMembers(this.organizationId);
      }
    });
  }

  loadMembers(organizationId: number): void {
    
    this.organizationService.getMembers(organizationId).subscribe(
      (data) => {
        this.members = data.members;
        console.log(data);
      },
      (error) => {
        console.error('Error fetching members:', error);
      }
    );
  }
}
