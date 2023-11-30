// src/app/user-profile/user-profile.component.ts
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserProfileService } from '../../service/user-profile.service';
import { UserProfile } from '../../model/user-profile';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  userProfile!: UserProfile;

  constructor(
    private route: ActivatedRoute,
    private userProfileService: UserProfileService
  ) {}

  ngOnInit(): void {
    const userId = 1; // assuming the user id is part of the route
    this.userProfileService.getUserProfile(userId)
      .subscribe(userProfile => this.userProfile = userProfile);
  }
}
