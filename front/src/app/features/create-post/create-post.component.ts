import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PostService, Subject } from '../posts/services/post.service';
import { Subject as RxSubject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { Location } from '@angular/common';

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.scss'],
})
export class CreatePostComponent implements OnDestroy, OnInit {
  private destroy$ = new RxSubject<void>();

  subjects: Subject[] = [];

  form = this.fb.group({
    title: ['', Validators.required],
    content: ['', Validators.required],
    subject: ['', Validators.required],
  });

  constructor(
    private fb: FormBuilder,
    private postService: PostService,
    private router: Router,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.postService
      .getAllSubjects()
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: (data) => (this.subjects = data),
        error: (err) => console.error('Erreur chargement subjects', err),
      });
  }

  submit(): void {
    if (this.form.valid) {
      const post = {
        title: this.form.value.title ?? '',
        content: this.form.value.content ?? '',
        subject: this.form.value.subject ?? '',
      };

      this.postService
        .createPost(post)
        .pipe(takeUntil(this.destroy$))
        .subscribe({
          next: () => this.router.navigate(['/posts']),
          error: (err) => console.error('Erreur création post:', err),
        });
    }
  }
  public goBack(): void {
    this.location.back();
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
