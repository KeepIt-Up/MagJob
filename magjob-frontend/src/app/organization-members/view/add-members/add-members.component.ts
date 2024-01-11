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
  addedUsers: any[] = [];
  searchText: string = '';

  constructor(private userService: UserService)  {}

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
    this.addedUsers.push(user);
  }
}
