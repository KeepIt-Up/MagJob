// src/app/user-settings/user-settings.service.ts
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { UserSettings } from '../model/user-settings';

@Injectable({
  providedIn: 'root'
})
export class UserSettingsService {
  private apiUrl = 'your-api-endpoint'; // replace with your API endpoint

  constructor(private http: HttpClient) {}

  updateUserSettings(userSettings: UserSettings): Observable<UserSettings> {
    const url = `${this.apiUrl}/user-settings/${userSettings.id}`;
    return this.http.put<UserSettings>(url, userSettings);
  }
}
