import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';

@Component({
  standalone: true,
  selector: 'app-admin-layout',
  templateUrl: './admin-layout.html',
  styleUrl: './admin-layout.css',
  imports: [RouterModule]
})
export class AdminLayoutComponent {

  currentYear = new Date().getFullYear();
  constructor(private router: Router) {}

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['/auth']);
  }
}
