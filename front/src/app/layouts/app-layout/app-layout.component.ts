import { Component, HostListener } from '@angular/core';
import { Router } from '@angular/router';
import { SessionService } from 'src/app/services/session.service';

@Component({
  selector: 'app-auth-layout',
  templateUrl: './app-layout.component.html',
  styleUrls: ['./app-layout.component.scss'],
})
export class AppLayoutComponent {
  menuOpen = false;
  innerWidth = window.innerWidth;

  constructor(private sessionService: SessionService, private router: Router) {}

  @HostListener('window:resize')
  onResize() {
    this.innerWidth = window.innerWidth;
    if (this.innerWidth > 768) this.menuOpen = false;
  }

  toggleMenu(): void {
    this.menuOpen = !this.menuOpen;
  }

  logout(): void {
    this.sessionService.logOut();
    this.router.navigate(['']);
    this.menuOpen = false;
  }
}
