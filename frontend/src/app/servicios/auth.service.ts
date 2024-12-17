import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loginUrl = 'http://localhost:8080/api/login';
  private usuariosUrl = 'http://localhost:8080/usuarios/lista';

  private authData:{usuario:string; password:string} | null = null;

  constructor(private http:HttpClient) { }

  login(usuario:string, password:string) {
    const headers=new HttpHeaders({'Content-Type': 'application/json'});
    const body = {usuario, password};
    this.guardarCredenciales(usuario,password);
    return this.http.post<any>(this.loginUrl, body, {headers});
  }

  guardarCredenciales(usuario:string, password:string):void {
    this.authData = {usuario, password};
    localStorage.setItem('authData', JSON.stringify(this.authData));
  }

  obtenerCredenciales():{usuario:string, password:string}|null {
    if(!this.authData) {
      console.log('Credenciales:', localStorage.getItem('usuario'))
      console.log('Credenciales:', localStorage.getItem('password'))
      const storeData = localStorage.getItem('authData');
      this.authData = storeData ? JSON.parse(storeData):null;
    }
    return this.authData;
  }

  getUsuarios():Observable<any> {
    const credenciales = this.obtenerCredenciales();
    if(!credenciales) {
      throw new Error('No se han encontrado las credenciales correctas, por favor iniciar sesión');
    }
    const headers = new HttpHeaders({
      'Authorization':'Basic ' + btoa(`${credenciales.usuario}:${credenciales.password}`)
    });
    return this.http.get(this.usuariosUrl, {headers});
  }

  deleteUsuario(idUsuario:number):Observable<any> {
    const urlEliminarUsuario = 'http://localhost:8080/usuarios/eliminar/'+ idUsuario;
    const credenciales = this.obtenerCredenciales();
    if(!credenciales) {
      throw new Error('No se han encontrado las credenciales correctas, por favor iniciar sesión');
    }
    const headers = new HttpHeaders({
      'Authorization':'Basic ' + btoa(`${credenciales.usuario}:${credenciales.password}`)
    });
    // this.http.delete(urlEliminarUsuario, { headers }).subscribe({
    //   next: (response) => console.log('Respuesta del servidor:', response),
    //   error: (error) => console.error('Error al eliminar usuario:', error)
    // });

    // return this.http.delete(urlEliminarUsuario, {headers})

    return this.http.delete(urlEliminarUsuario, {headers}).pipe(
      tap({
        next: (response) => console.log('Respuesta del servidor:', response),
        error: (error) => console.error('Error al eliminar usuario:', error)
      })
    )
  }
}
