import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Comment {
  id?: number;
  content: string;
  userName: string;
  postId: number;
  createdAt?: string;
}

@Injectable({
  providedIn: 'root',
})
export class CommentService {
  private baseUrl = '/api/comment';

  constructor(private http: HttpClient) {}

  create(comment: Comment): Observable<any> {
    return this.http.post(this.baseUrl, comment);
  }

  getByPost(postId: number): Observable<Comment[]> {
    return this.http.get<Comment[]>(`${this.baseUrl}/post/${postId}`);
  }
}
