// import { Component } from '@angular/core';
// import { CommonModule } from '@angular/common';
// import { FormsModule } from '@angular/forms';
// import { Router, RouterModule } from '@angular/router';
// import { AuthService } from '../../services/auth.service';

// @Component({
//   selector: 'app-register',
//   standalone: true,
//   imports: [CommonModule, FormsModule, RouterModule],
//   templateUrl: './register.html',
//   styleUrl: './register.css'
// })
// export class RegisterComponent {

//   name = '';
//   email = '';
//   password = '';
//   successMessage = '';
//   errorMessage = '';

//   constructor(
//     private authService: AuthService,
//     private router: Router
//   ) {}

//   register() {
//     console.log('Register clicked'); // 🔍 DEBUG LINE

//     this.authService.register({
//       name: this.name,
//       email: this.email,
//       password: this.password
//     }).subscribe({
//       next: () => {
//         this.successMessage = 'Registration successful';
//         setTimeout(() => this.router.navigate(['/login']), 1500);
//       },
//       error: (err) => {
//         console.error(err);
//         this.errorMessage = 'Registration failed';
//       }
//     });
//   }
// }
import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './register.html',
  styleUrl: './register.css'
})
export class RegisterComponent {

  name = '';
  email = '';
  password = '';

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  register() {
    console.log('REGISTER CLICKED');

    this.authService.register({
      name: this.name,
      email: this.email,
      password: this.password
    }).subscribe({
      next: () => {
        this.router.navigate(['/login']);
      },
      error: err => {
        console.error(err);
      }
    });
  }
}
