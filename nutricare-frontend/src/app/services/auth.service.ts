import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class AuthService {

    private baseUrl = 'http://localhost:8080/api/auth';

    constructor(private http: HttpClient) { }

    login(data: any) {
        return this.http.post<any>(`${this.baseUrl}/login`, data);
    }

    register(data: any) {
        return this.http.post(`${this.baseUrl}/register`, data);
    }

    saveToken(token: string) {
        localStorage.setItem('token', token);
    }

    getToken(): string | null {
        return localStorage.getItem('token');
    }

    logout() {
        localStorage.removeItem('token');
    }

    isLoggedIn(): boolean {
        return !!this.getToken();
    }

    getRoleFromToken(): string | null {
  const token = this.getToken();
  if (!token) return null;

  const payload = JSON.parse(atob(token.split('.')[1]));

  if (payload.role) return payload.role;

  if (payload.roles && payload.roles.length > 0) {
    return 'ROLE_' + payload.roles[0];
  }

  if (payload.authorities && payload.authorities.length > 0) {
    return payload.authorities[0].startsWith('ROLE_')
      ? payload.authorities[0]
      : 'ROLE_' + payload.authorities[0];
  }

  return null;
}

}
