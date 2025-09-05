import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { PostService } from './services/post.service';
import { Post } from 'src/app/interfaces/post.interface';

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.scss'],
})
export class PostsComponent implements OnInit, OnDestroy {
  posts: Post[] = [];
  isAsc = false;

  private destroy$ = new Subject<void>();

  constructor(private postService: PostService) {}

  ngOnInit(): void {
    this.postService
      .getAll()
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: (data) => (this.posts = data),
        error: (err) => console.error('Erreur chargement posts', err),
      });
  }

  toggleSort(): void {
    this.isAsc = !this.isAsc;
    this.posts = [...this.posts].sort((a, b) => {
      const dateA = new Date(a.createdAt).getTime();
      const dateB = new Date(b.createdAt).getTime();
      return this.isAsc ? dateA - dateB : dateB - dateA;
    });
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
