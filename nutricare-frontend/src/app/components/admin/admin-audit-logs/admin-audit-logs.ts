import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminAuditService } from '../../../services/admin-audit.services';

@Component({
  selector: 'app-admin-audit-logs',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './admin-audit-logs.html',
  styleUrls: ['./admin-audit-logs.css']
})
export class AdminAuditLogsComponent implements OnInit {

  logs: any[] = [];
  loading = true;

  constructor(private auditService: AdminAuditService) {}

  ngOnInit() {
    this.auditService.getLogs().subscribe({
      next: data => {
        this.logs = data;
        this.loading = false;
      },
      error: () => {
        this.loading = false;
      }
    });
  }
}
