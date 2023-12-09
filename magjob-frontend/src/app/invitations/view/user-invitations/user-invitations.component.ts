import { Component, OnInit } from '@angular/core';
import { InvitationsService } from 'src/app/invitations/service/invitations.service';

@Component({
  selector: 'app-user-invitations',
  templateUrl: './user-invitations.component.html',
  styleUrls: ['./user-invitations.component.css']
})
export class UserInvitationsComponent implements OnInit {

  invitations: any[] = [];
  userId: number = 1;

  constructor(private organizationService: InvitationsService) {}

  ngOnInit(): void {
    this.loadInvitations();
  }

  loadInvitations() {
    this.organizationService.getInvitations(this.userId).subscribe(
      (data) => {
        this.invitations = data;
      },
      (error) => {
        console.error('Błąd pobierania zaproszeń:', error);
      }
    );
  }

  accept(): void
  {

  }
  deny(): void 
  {

  }
}
