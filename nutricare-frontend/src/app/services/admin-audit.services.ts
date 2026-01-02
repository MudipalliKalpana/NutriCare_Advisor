import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class AdminAuditService {

  private api = 'http://localhost:8080/api/admin/audit-logs';

  constructor(private http: HttpClient) {}

  getLogs() {
    return this.http.get<any[]>(this.api);
  }
}
