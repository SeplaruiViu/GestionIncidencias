import { Component } from '@angular/core';
import { AutenticacionService } from '../../servicios/autenticacion.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  usuario: string='';
  password: string='';
  errorMensaje: string='';

constructor(private autenticationService: AutenticationService, private router:Router){}

login(): void {
  this.autenticationService.login(this.usuario, this.password).subscribe(
    response=> {
      console.log('Login correcto', response);
    this.router.navigate(['/login']);
},
  error=> {
    console.error('Login erróneo', error);
  this.errorMensaje = 'Usuario o contrasenya incorrectos';
}
);
}
}
