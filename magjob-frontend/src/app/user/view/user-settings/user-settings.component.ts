// src/app/user-settings/user-settings.component.ts
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserSettingsService } from '../../service/user-settings.service';
import { UserSettings } from '../../model/user-settings';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-user-settings',
  templateUrl: './user-settings.component.html',
  styleUrls: ['./user-settings.component.css']
})
export class UserSettingsComponent implements OnInit {
  userSettingsForm: FormGroup = this.formBuilder.group({});
  userId: number;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private userSettingsService: UserSettingsService
  ) {
    this.userId = +this.route.snapshot.paramMap.get('userId')!;
  }

  ngOnInit(): void {
    this.userSettingsForm = this.formBuilder.group({
      password: this.formBuilder.group({
        currentPassword: ['', Validators.required],
        newPassword: ['', Validators.required],
        confirmNewPassword: ['', Validators.required]
      }),
      email: ['', [Validators.required, Validators.email]],
      name: this.formBuilder.group({
        firstName: ['', Validators.required],
        surname: ['', Validators.required]
      })
    });
  }

  onSubmit(): void {
    if (this.userSettingsForm.valid) {
      const userSettings: UserSettings = {
        id: 1, // replace with the actual user ID
        username: 'username', // replace with the actual username
        password: this.userSettingsForm.get('password.newPassword')?.value,
        email: this.userSettingsForm.get('email')?.value,
        firstName: this.userSettingsForm.get('name.firstName')?.value,
        surname: this.userSettingsForm.get('name.surname')?.value
      };

      this.userSettingsService.updateUserSettings(userSettings)
        .subscribe(updatedUserSettings => {
          // Handle successful update
          console.log('User settings updated:', updatedUserSettings);
        });
    }
  }
}
