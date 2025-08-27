import { Component, HostListener, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from './features/auth/services/auth.service';
import { User } from './interfaces/user.interface';
import { SessionService } from './services/session.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  constructor(
    private authService: AuthService,
    public router: Router,
    private sessionService: SessionService
  ) {}

  public ngOnInit(): void {
    this.autoLog();
  }

  public $isLogged(): Observable<boolean> {
    return this.sessionService.$isLogged();
  }

  public logout(): void {
    this.sessionService.logOut();
    this.router.navigate(['']);
  }

  public autoLog(): void {
    this.authService.me().subscribe(
      (user: User) => {
        this.sessionService.logIn(user);
      },
      (_) => {
        this.sessionService.logOut();
      }
    );
  }

  public innerWidth = window.innerWidth;

  @HostListener('window:resize', ['$event'])
  onResize() {
    this.innerWidth = window.innerWidth;
  }

  get showToolbar(): boolean {
    const url = this.router.url;

    if (url === '/') {
      return false;
    }

    if (
      this.innerWidth <= 768 &&
      (url.includes('/login') || url.includes('/register'))
    ) {
      return false;
    }

    return true;
  }

  public isAuthPage(): boolean {
    return (
      this.router.url.includes('login') || this.router.url.includes('register')
    );
  }
}
