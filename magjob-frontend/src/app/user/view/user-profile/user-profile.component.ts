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
  userId: number | null = null;
  constructor(
    private route: ActivatedRoute,
    private userProfileService: UserProfileService
  ) {}

  ngOnInit(): void {
    // Access the userId from the URL parameters
    const userIdParam = this.route.snapshot.paramMap.get('id');
    if(userIdParam !== null)
    {
      this.userId = +userIdParam;
      this.userProfileService.getUserProfile(this.userId)
      .subscribe(userProfile => this.userProfile = userProfile);
    }
    else{
      console.log("Damian");
    }
    
  }
}
