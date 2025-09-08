import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Theme } from 'src/app/interfaces/theme.interface';

@Injectable({
  providedIn: 'root',
})
export class ThemeService {
  private baseUrl = '/api/subjects';

  constructor(private http: HttpClient) {}

  public getAll(): Observable<Theme[]> {
    return this.http.get<Theme[]>(this.baseUrl);
  }
}
