import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './components/app/app.component';
import { NavMenuComponent } from './components/nav-menu/nav-menu.component';
import { LoginComponent } from './login/view/login.component';
import { RegisterComponent } from './register/view/register.component';
import { HomeComponent } from './home/home.component';
import { FooterComponent } from './components/footer/footer.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { UserProfileComponent } from './user/view/user-profile/user-profile.component';
import { UserSettingsComponent } from './user/view/user-settings/user-settings.component';
import { OrganizationHomePageComponent } from './organization/view/organization-home-page/organization-home-page.component';
import { OrganizationSettingsComponent } from './organization/view/organization-settings/organization-settings.component';
import { JwtInterceptor } from './jwt/jwt.interceptor';
import { UserOrganizationComponent } from './user/view/user-organization/user-organization.component';
import { OrganizationCreationComponent } from './organization/view/organization-creation/organization-creation.component';
import { NoOrganizationComponent } from './organization/view/no-organization/no-organization.component';
import { UserInvitationsComponent } from './invitations/view/user-invitations/user-invitations.component';
import { OrganizationMembersComponent } from './organization-members/view/organization-members/organization-members.component';
import { ListMembersComponent } from './organization-members/view/list-members/list-members.component';
import { OrganizatonNavComponent } from './organization/organizaton-nav/organizaton-nav.component';
import { OrganizationComponent } from './organization/organization/organization.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavMenuComponent,
    LoginComponent,
    RegisterComponent,
    FooterComponent,
    UserProfileComponent,
    UserSettingsComponent,
    OrganizationHomePageComponent,
    OrganizationSettingsComponent,
    UserOrganizationComponent,
    OrganizationCreationComponent,
    NoOrganizationComponent,
    UserInvitationsComponent,
    OrganizationMembersComponent,
    ListMembersComponent,
    OrganizatonNavComponent,
    OrganizationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    RouterModule.forRoot([
    ]),
    ReactiveFormsModule,
    HttpClientModule,
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true,
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
