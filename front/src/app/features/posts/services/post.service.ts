// src/app/features/posts/services/post.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Post } from 'src/app/interfaces/post.interface';

@Injectable({
  providedIn: 'root',
})
export class PostService {
  private baseUrl = '/api/posts';

  constructor(private http: HttpClient) {}

  public getAll(): Observable<Post[]> {
    return this.http.get<Post[]>(this.baseUrl);
  }
}
