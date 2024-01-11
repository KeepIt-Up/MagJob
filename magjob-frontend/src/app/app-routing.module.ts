import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserProfileComponent } from './user/view/user-profile/user-profile.component';
import { UserSettingsComponent } from './user/view/user-settings/user-settings.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/view/login.component';
import { RegisterComponent } from './register/view/register.component';
import { NoOrganizationComponent } from './organization/view/no-organization/no-organization.component';
import { AuthGuard } from './jwt/auth.guard';
import { OrganizationCreationComponent } from './organization/view/organization-creation/organization-creation.component';
import { OrganizationComponent } from './organization/organization/organization.component';
import { OrganizationHomePageComponent } from './organization/view/organization-home-page/organization-home-page.component';
import { ChooseOrganizationComponent } from './organization/view/choose-organization/choose-organization.component';
import { ListMembersComponent } from './organization-members/view/list-members/list-members.component';
import { AddMembersComponent } from './organization-members/view/add-members/add-members.component';

const routes: Routes = [
  { path: '', component: HomeComponent, pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'user/:id', component: UserProfileComponent, canActivate: [AuthGuard] },
  { path: 'user/:userId/settings', component: UserSettingsComponent, canActivate: [AuthGuard] },
  { path: 'create-organization', component: OrganizationCreationComponent, canActivate: [AuthGuard] },
  { path: 'noorganization', component: NoOrganizationComponent, canActivate: [AuthGuard] },
  { path: 'chooseorganization', component: ChooseOrganizationComponent, canActivate: [AuthGuard] },
  {
    path: 'organization/:id',
    component: OrganizationComponent,
    children: [
      { path: 'home', component: OrganizationHomePageComponent },
      { path: 'schedule', component: OrganizationHomePageComponent },
      { path: 'members', component: ListMembersComponent },
      { path: 'settings', component: OrganizationHomePageComponent },
      { path: 'tasks', component: OrganizationHomePageComponent },
      { path: 'documents', component: OrganizationHomePageComponent },
      { path: 'addMembers',component: AddMembersComponent}
    ]
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
