import { UserService } from './../../user/service/user.service';
import { AuthService } from './../../jwt/auth.service';
import {Component, OnInit} from '@angular/core';
import {NavigationEnd, Router} from "@angular/router";
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-nav-menu',
  templateUrl: './nav-menu.component.html',
  styleUrls: ['./nav-menu.component.css']
})
export class NavMenuComponent implements OnInit{
  constructor(private authService: AuthService, private userService: UserService, private router: Router) {

  }

  isExpanded = false;
  currentRoute = "none" ;
  options: number[] = [1, 2, 3];
  selectedOption = 0;


  ngOnInit(): void {
    this.router.events.pipe(
      filter((event): event is NavigationEnd => event instanceof NavigationEnd)
    ).subscribe((event: NavigationEnd) => {
      this.setCurrentRoute(event.urlAfterRedirects);
    });
  }


  setCurrentRoute(url: string): void {
    this.currentRoute = url;
  }


  isLoggedIn(): boolean {
    return this.authService.isAuthenticated();
  }

  getUserId(): number | null {
    const currentUser = this.userService.getCurrentUserId();
    return currentUser ? currentUser : null;
  }

  logout(): void {
    this.authService.logout();
    this.userService.clearCurrentUser();
    // Optionally, navigate to the login page or another route
    // this.router.navigate(['/login']);
  }

  collapse() {
    this.isExpanded = false;
  }

  toggle() {
    this.isExpanded = !this.isExpanded;
  }
}
