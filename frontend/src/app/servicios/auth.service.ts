import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { Usuario } from '../modelos/usuario-interface';
import { Rol } from '../modelos/rol-interface';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loginUrl = 'http://localhost:8080/api/login';
  private usuariosUrl = 'http://localhost:8080/usuarios/lista';
  private rolesUrl = 'http://localhost:8080/roles/lista';
  private crearUsuarioUrl = 'http://localhost:8080/usuarios/nuevo';
  private actualizarUsuarioUrl = 'http://localhost:8080/usuarios/actualizar';
  private detalleUsuarioUrl = 'http://localhost:8080/usuarios/detalle/';

  private eliminarRolUrl = 'http://localhost:8080/roles/eliminar/'
  private crearRolUrl = 'http://localhost:8080/roles/nuevo';
  private detalleRolUrl = 'http://localhost:8080/roles/detalle/';
  private actualizarRolUrl = 'http://localhost:8080/roles/actualizar'

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

    return this.http.delete(urlEliminarUsuario, {headers, responseType:'text'}).pipe(
      tap({
        next: (response) => console.log('Respuesta del servidor:', response),
        error: (error) => console.error('Error al eliminar usuario:', error)
      })
    )
  }

  crearUsuario(usuario: Usuario):Observable<any> {
    const credenciales = this.obtenerCredenciales();
    if(!credenciales) {
      throw new Error('No se han encontrado las credenciales correctas, por favor iniciar sesión');
    }
    const headers = new HttpHeaders({
      'Authorization':'Basic ' + btoa(`${credenciales.usuario}:${credenciales.password}`)
    });

    return this.http.post(this.crearUsuarioUrl, usuario, {headers});
  }

  detalleUsuario(idUsuario: number): Observable<Usuario> {
    const credenciales = this.obtenerCredenciales();
    if(!credenciales) {
      throw new Error('No se han encontrado las credenciales correctas, por favor iniciar sesión');
    }
    const headers = new HttpHeaders({
      'Authorization':'Basic ' + btoa(`${credenciales.usuario}:${credenciales.password}`)
    });
    console.log(this.detalleUsuarioUrl+idUsuario);
    return this.http.get<Usuario>(this.detalleUsuarioUrl+idUsuario, {headers});
  }
  actualizarUsuario(idUsuario: number, usuario: Usuario): Observable<any> {
    const credenciales = this.obtenerCredenciales();
    if(!credenciales) {
      throw new Error('No se han encontrado las credenciales correctas, por favor iniciar sesión');
    }
    const headers = new HttpHeaders({
      'Authorization':'Basic ' + btoa(`${credenciales.usuario}:${credenciales.password}`)
    });

    return this.http.put(this.actualizarUsuarioUrl + '/' + idUsuario, usuario, {headers});
  }

  getRoles():Observable<any> {
    const credenciales = this.obtenerCredenciales();
    if(!credenciales) {
      throw new Error('No se han encontrado las credenciales correctas, por favor iniciar sesión');
    }
    const headers = new HttpHeaders({
      'Authorization':'Basic ' + btoa(`${credenciales.usuario}:${credenciales.password}`)
    });
    return this.http.get(this.rolesUrl, {headers});
  }

  crearRol(rol: Rol):Observable<any> {
    const credenciales = this.obtenerCredenciales();
    if(!credenciales) {
      throw new Error('No se han encontrado las credenciales correctas, por favor iniciar sesión');
    }
    const headers = new HttpHeaders({
      'Authorization':'Basic ' + btoa(`${credenciales.usuario}:${credenciales.password}`)
    });

    return this.http.post(this.crearRolUrl, rol, {headers});
  }

  eliminarRol(idRol: number):Observable<any> {
    const credenciales = this.obtenerCredenciales();
    if(!credenciales) {
      throw new Error('No se han encontrado las credenciales correctas, por favor iniciar sesión');
    }
    const headers = new HttpHeaders({
      'Authorization':'Basic ' + btoa(`${credenciales.usuario}:${credenciales.password}`)
    });

    return this.http.delete(this.eliminarRolUrl+idRol, {headers, responseType:'text'}).pipe(
      tap({
        next: (response)=> console.log('Respuesta del servidor borrado Rol: ', response),
        error: (error) => console.error('Error al eliminar el rol: ', error)
      })
    );
  }


  detalleRol(idRol: number): Observable<Rol> {
    const credenciales = this.obtenerCredenciales();
    if(!credenciales) {
      throw new Error('No se han encontrado las credenciales correctas, por favor iniciar sesión');
    }
    const headers = new HttpHeaders({
      'Authorization':'Basic ' + btoa(`${credenciales.usuario}:${credenciales.password}`)
    });
    console.log(this.detalleRolUrl+idRol);
    return this.http.get<Rol>(this.detalleRolUrl+idRol, {headers});
  }

  actualizarRol(idRol: number, rol: Rol): Observable<any> {
    const credenciales = this.obtenerCredenciales();
    if(!credenciales) {
      throw new Error('No se han encontrado las credenciales correctas, por favor iniciar sesión');
    }
    const headers = new HttpHeaders({
      'Authorization':'Basic ' + btoa(`${credenciales.usuario}:${credenciales.password}`)
    });

    return this.http.put(this.actualizarRolUrl + '/' + idRol, rol, {headers});
  }
}
