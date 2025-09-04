// src/app/features/posts/posts.component.ts
import { Component, OnInit } from '@angular/core';
import { PostService } from './services/post.service';
import { Post } from 'src/app/interfaces/post.interface';

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.scss'],
})
export class PostsComponent implements OnInit {
  posts: Post[] = [];
  isAsc = false;

  constructor(private postService: PostService) {}

  ngOnInit(): void {
    this.postService.getAll().subscribe({
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
}
