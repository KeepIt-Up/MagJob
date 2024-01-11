import { Invitation } from './../../../invitations/model/invitation';
import { OrganizationService } from 'src/app/organization/service/organization.service';
import { OrganizationComponent } from './../../../organization/organization/organization.component';
import { InvitationsService } from 'src/app/invitations/service/invitations.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/user/model/user';
import { UserService } from 'src/app/user/service/user.service';

@Component({
  selector: 'app-add-members',
  templateUrl: './add-members.component.html',
  styleUrls: ['./add-members.component.css']
})
export class AddMembersComponent implements OnInit{
  users: User[] = [];
  filteredUsers: User[] = [];
  searchText: string = '';

  constructor(private userService: UserService, private invitationsService: InvitationsService, private organizationService: OrganizationService)  {}

  ngOnInit(): void {
    this.searchUsers();
  }


  searchUsers() {
    // Symulacja pobierania danych z serwisu
    this.userService.getUsers().subscribe((data) => {
      this.users = data.users;
      console.log(this.users);
      this.filterUsers();
    });
  }

  filterUsers() {
    this.filteredUsers = this.users.filter((user) =>
      user.email.toLowerCase().includes(this.searchText.toLowerCase())
    );
  }

  inviteUser(user: any) {
    const invitation: Invitation = {
      user: user.id,
      organization: this.organizationService.getCurrentOrganizationId().toString()
    }
    this.invitationsService.invite(invitation).subscribe(
      (response) => {
        // Handle successful response
        console.log('Response:', response);
      },
      (error) => {
        // Handle error
        console.error('Error:', error);
      }
    );
  }
}
