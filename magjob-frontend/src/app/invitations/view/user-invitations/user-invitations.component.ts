import { Invitation } from './../../model/invitation';
import { OrganizationService } from './../../../organization/service/organization.service';
import { UserService } from 'src/app/user/service/user.service';
import { Component, OnInit } from '@angular/core';
import { InvitationsService } from 'src/app/invitations/service/invitations.service';

@Component({
  selector: 'app-user-invitations',
  templateUrl: './user-invitations.component.html',
  styleUrls: ['./user-invitations.component.css']
})
export class UserInvitationsComponent implements OnInit {

  invitations: any[] = [];
  userId: number | null = null;

  constructor(private invitationService: InvitationsService, private userService: UserService, private organizationService: OrganizationService) {}

  ngOnInit(): void {
    this.loadInvitations();
  }

  loadInvitations() {
    this.userId = this.userService.getCurrentUserId();
    if(this.userId != null)
    {
      this.invitationService.getInvitations(this.userId).subscribe(
        (data) => {
          console.log(data);
          this.invitations = data.invitations;
        },
        (error) => {
          console.error('Błąd pobierania zaproszeń:', error);
        }
      );
    }
  }

  accept(Invitation: Invitation): void
  {

  }
  deny(Invitation: Invitation): void 
  {

  }
}
