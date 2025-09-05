// src/app/features/posts/services/post.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Post } from 'src/app/interfaces/post.interface';

export interface PostRequest {
  title: string;
  content: string;
  subject: string;
}
export interface Subject {
  id: number;
  name: string;
}
@Injectable({
  providedIn: 'root',
})
export class PostService {
  private baseUrl = '/api/posts';
  private subjectsUrl = '/api/subjects';

  constructor(private http: HttpClient) {}

  public getAll(): Observable<Post[]> {
    return this.http.get<Post[]>(this.baseUrl);
  }

  createPost(post: PostRequest): Observable<any> {
    const formData = new FormData();
    formData.append('title', post.title);
    formData.append('content', post.content);
    formData.append('subject', post.subject);

    return this.http.post<any>(this.baseUrl, formData);
  }
  public getAllSubjects(): Observable<Subject[]> {
    return this.http.get<Subject[]>(this.subjectsUrl);
  }
}
