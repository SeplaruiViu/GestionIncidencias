import { Component } from '@angular/core';
import { AuthService } from '../../servicios/auth.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  usuario: string = '';
  password: string = '';
  errorMensaje: string = '';

  constructor(private authService: AuthService, private router:Router) {}

  login():void {
    this.authService.login(this.usuario, this.password).subscribe({
      next:(response) => {
        console.log('Respuesta del servidor: ', response);
        localStorage.setItem('usuario', response.username);
        localStorage.setItem('password', response.password);
        this.router.navigate(['/inicio']);
      },
      error:(error) => {
        console.error('Error en el login: ', error);
        this.errorMensaje = 'Usuario o contraseña incorrectos';
      }
    })
  }
}
