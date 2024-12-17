import { CanActivate, Router } from '@angular/router';
import { Injectable } from '@angular/core';

// export const authGuard: CanActivateFn = (route, state) => {
//   return true;
// };


@Injectable({
  providedIn: 'root'
})

export class AuthGuard implements CanActivate {

  constructor(private router: Router) {};

  canActivate():boolean {
    const usuario = localStorage.getItem('usuario');
    if(usuario) {
      return true;
    } else {
      this.router.navigate(['/login']);
      return false;
    }
  }
}
