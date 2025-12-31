import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  standalone: true,
  imports: [RouterModule],
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.html',
  styleUrl: './admin-dashboard.css'
})
export class AdminDashboardComponent {
  
  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  goHome() {
    this.router.navigate(['/admin/home']);
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
