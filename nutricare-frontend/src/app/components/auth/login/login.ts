import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './login.html',
  styleUrls: ['./login.css']
})
export class LoginComponent {

  email = '';
  password = '';

  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  login() {
    this.authService.login({
      email: this.email,
      password: this.password
    }).subscribe({
      next: (res: any) => {
  this.authService.saveToken(res.token);

  const role = this.authService.getRoleFromToken();

  if (role === 'ADMIN') {
    this.router.navigate(['/admin/home']);
  } else {
    this.router.navigate(['/user/home']);
  }
}

,
      error: () => {
        alert('Invalid credentials');
      }
    });
  }
}
