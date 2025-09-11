import { Component, OnInit } from '@angular/core';
import { ThemeService } from './services/theme.service';
import { Theme } from 'src/app/interfaces/theme.interface';
import {
  SubscriptionService,
  SubscriptionDTO,
} from './services/subscription.service';
import { SessionService } from 'src/app/services/session.service';

@Component({
  selector: 'app-theme',
  templateUrl: './theme.component.html',
  styleUrls: ['./theme.component.scss'],
})
export class ThemeComponent implements OnInit {
  themes: Theme[] = [];
  userId!: number;
  userSubscriptions: number[] = [];

  constructor(
    private themeService: ThemeService,
    private subscriptionService: SubscriptionService,
    private sessionService: SessionService
  ) {}

  ngOnInit(): void {
    this.userId = this.sessionService.user?.id ?? 0;

    // Charger les thèmes
    this.themeService.getAll().subscribe({
      next: (data) => (this.themes = data),
      error: (err) => console.error('Erreur chargement thèmes', err),
    });

    if (this.userId) {
      this.subscriptionService.getByUser(this.userId).subscribe({
        next: (subs) => (this.userSubscriptions = subs.map((s) => s.subjectId)),
        error: (err) => console.error('Erreur chargement abonnements', err),
      });
    }
  }

  isSubscribed(subjectId: number): boolean {
    return this.userSubscriptions.includes(subjectId);
  }

  subscribe(subjectId: number): void {
    if (!this.userId) return;

    const subscription: SubscriptionDTO = {
      userId: this.userId,
      subjectId: subjectId,
    };

    this.subscriptionService.create(subscription).subscribe({
      next: (res) => {
        this.userSubscriptions.push(res.subjectId);
      },
      error: (err) => console.error('Erreur abonnement', err),
    });
  }
}
