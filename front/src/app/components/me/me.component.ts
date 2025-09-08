import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { AuthService } from 'src/app/features/auth/services/auth.service';
import { User } from 'src/app/interfaces/user.interface';
import { UserService } from './service/user.service';

@Component({
  selector: 'app-me',
  templateUrl: './me.component.html',
  styleUrls: ['./me.component.scss'],
})
export class MeComponent implements OnInit {
  public user: User | undefined;

  form = this.fb.group({
    name: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    password: [''], // vide par défaut
  });

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.authService.me().subscribe((user: User) => {
      this.user = user;
      this.form.patchValue({
        name: user.name,
        email: user.email,
      });
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

      this.userService.updateUser(this.user.id, updatedUser).subscribe({
        next: () => alert('Profil mis à jour avec succès, déconnexion'),
        error: (err) => console.error('Erreur lors de la mise à jour', err),
      });
    }
  }
}
