import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserProfileComponent } from './user/view/user-profile/user-profile.component';
import { UserSettingsComponent } from './user/view/user-settings/user-settings.component';

const routes: Routes = [
  { path: 'user/:id', component: UserProfileComponent },
  {
    path: 'user/:userId/settings',
    component: UserSettingsComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
