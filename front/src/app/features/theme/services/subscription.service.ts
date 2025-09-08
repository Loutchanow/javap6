// src/app/features/theme/services/subscription.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface SubscriptionDTO {
  id?: number;
  userId: number;
  subjectId: number;
}

@Injectable({
  providedIn: 'root',
})
export class SubscriptionService {
  private baseUrl = '/api/subscriptions';

  constructor(private http: HttpClient) {}

  create(subscription: SubscriptionDTO): Observable<SubscriptionDTO> {
    return this.http.post<SubscriptionDTO>(this.baseUrl, subscription);
  }

  getByUser(userId: number): Observable<SubscriptionDTO[]> {
    return this.http.get<SubscriptionDTO[]>(`${this.baseUrl}/user/${userId}`);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
