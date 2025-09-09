import { SessionService } from './../../services/session.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { AuthService } from 'src/app/features/auth/services/auth.service';
import { User } from 'src/app/interfaces/user.interface';
import { UserService } from './service/user.service';
import {
  SubscriptionService,
  SubscriptionDTO,
} from 'src/app/features/theme/services/subscription.service';
import { ThemeService } from 'src/app/features/theme/services/theme.service';
import { Theme } from 'src/app/interfaces/theme.interface';

@Component({
  selector: 'app-me',
  templateUrl: './me.component.html',
  styleUrls: ['./me.component.scss'],
})
export class MeComponent implements OnInit {
  public user: User | undefined;
  public subscribedThemes: Theme[] = [];

  form = this.fb.group({
    name: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    password: [''],
  });

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private userService: UserService,
    private subscriptionService: SubscriptionService,
    private themeService: ThemeService,
    private sessionService: SessionService
  ) {}

  ngOnInit(): void {
    this.authService.me().subscribe((user: User) => {
      this.user = user;
      this.form.patchValue({
        name: user.name,
        email: user.email,
      });

      this.loadSubscriptions();
    });
  }

  private loadSubscriptions(): void {
    if (!this.user) return;

    this.subscriptionService.getByUser(this.user.id).subscribe({
      next: (subscriptions: SubscriptionDTO[]) => {
        this.themeService.getAll().subscribe((themes: Theme[]) => {
          this.subscribedThemes = themes.filter((theme) =>
            subscriptions.some((sub) => sub.subjectId === theme.id)
          );
        });
      },
      error: (err) =>
        console.error('Erreur lors du chargement des abonnements', err),
    });
  }

  public back() {
    window.history.back();
  }
  public updateUser(): void {
    if (this.user) {
      const updatedUser: Partial<User> = {
        name: this.form.value.name ?? '',
        email: this.form.value.email ?? '',
        password: this.form.value.password?.trim() || undefined,
      };

      const hasChanged =
        updatedUser.name !== this.user.name ||
        updatedUser.email !== this.user.email ||
        (!!updatedUser.password && updatedUser.password.length > 0);

      if (!hasChanged) {
        alert('Aucune modification détectée');
        return;
      }

      this.userService.updateUser(this.user.id, updatedUser).subscribe({
        next: () => {
          alert('Profil mis à jour avec succès, vous allez être déconnecté');
          this.sessionService.logOut();
          window.location.href = '/login';
        },
        error: (err) => console.error('Erreur lors de la mise à jour', err),
      });
    }
  }

  public unsubscribe(themeId: number): void {
    if (!this.user) return;

    this.subscriptionService.getByUser(this.user.id).subscribe((subs) => {
      const sub = subs.find((s) => s.subjectId === themeId);
      if (sub?.id) {
        this.subscriptionService.delete(sub.id).subscribe(() => {
          this.subscribedThemes = this.subscribedThemes.filter(
            (t) => t.id !== themeId
          );
        });
      }
    });
  }
}
