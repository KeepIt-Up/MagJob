import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserProfileComponent } from './user/view/user-profile/user-profile.component';
import { UserSettingsComponent } from './user/view/user-settings/user-settings.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/view/login.component';
import { RegisterComponent } from './register/view/register.component';
import { AuthGuard } from './jwt/auth.guard';

const routes: Routes = [
  { path: '', component: HomeComponent, pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'user/:id', component: UserProfileComponent, canActivate: [AuthGuard] },
  { path: 'user/:userId/settings', component: UserSettingsComponent, canActivate: [AuthGuard] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
