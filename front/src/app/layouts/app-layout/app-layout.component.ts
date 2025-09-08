import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { SessionService } from 'src/app/services/session.service';

@Component({
  selector: 'app-app-layout',
  templateUrl: './app-layout.component.html',
  styleUrls: ['./app-layout.component.scss'],
})
export class AppLayoutComponent {
  constructor(private sessionService: SessionService, private router: Router) {}

  logout(): void {
    this.sessionService.logOut();
    this.router.navigate(['/']);
  }
}
