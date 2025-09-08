import { Component, HostListener, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/features/auth/services/auth.service';
import { User } from 'src/app/interfaces/user.interface';
import { SessionService } from 'src/app/services/session.service';

@Component({
  selector: 'app-auth-layout',
  templateUrl: './auth-layout.component.html',
  styleUrls: ['./auth-layout.component.scss'],
})
export class AuthLayoutComponent implements OnInit {
  public innerWidth = window.innerWidth;

  constructor(
    private authService: AuthService,
    public router: Router,
    private sessionService: SessionService
  ) {}

  ngOnInit(): void {
    this.autoLog();
  }

  public $isLogged(): Observable<boolean> {
    return this.sessionService.$isLogged();
  }

  public logout(): void {
    this.sessionService.logOut();
    this.router.navigate(['']);
  }

  private autoLog(): void {
    this.authService.me().subscribe(
      (user: User) => {
        this.sessionService.logIn(user);
      },
      () => {
        this.sessionService.logOut();
      }
    );
  }

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
