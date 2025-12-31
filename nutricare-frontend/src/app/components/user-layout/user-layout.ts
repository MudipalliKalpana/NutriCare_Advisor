import { Component } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-user-layout',
  standalone: true,
  imports: [CommonModule, RouterOutlet],
  templateUrl: './user-layout.html'
})
export class UserLayoutComponent {

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  goHome() {
    this.router.navigate(['/user/home']);
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
