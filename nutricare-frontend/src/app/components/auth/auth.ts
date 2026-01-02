import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule, Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-auth',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './auth.html',
  styleUrls: ['./auth.css']
})
export class AuthComponent {
  isFlipped = false;

  // Separate form fields
  loginEmail = '';
  loginPassword = '';
  registerName = '';
  registerEmail = '';
  registerPassword = '';

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  flipCard() {
    this.isFlipped = !this.isFlipped;
    // reset fields when flipping
    this.loginEmail = '';
    this.loginPassword = '';
    this.registerName = '';
    this.registerEmail = '';
    this.registerPassword = '';
  }

  login() {
    this.authService.login({
      email: this.loginEmail,
      password: this.loginPassword
    }).subscribe({
      next: (res: any) => {
        this.authService.saveToken(res.token);
        const role = this.authService.getRoleFromToken();
        if (role === 'ADMIN') {
          this.router.navigate(['/admin/home']);
        } else {
          this.router.navigate(['/user/home']);
        }
      },
      error: () => {
        alert('Invalid credentials');
      }
    });
  }

  register() {
    this.authService.register({
      name: this.registerName,
      email: this.registerEmail,
      password: this.registerPassword
    }).subscribe({
      next: () => {
        alert('Registration successful! Please log in.');
        this.flipCard();
      },
      error: () => {
        alert('Registration failed');
      }
    });
  }
}
