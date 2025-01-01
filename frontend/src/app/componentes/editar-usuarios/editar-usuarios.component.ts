import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { MenuComponent } from '../menu/menu.component';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, FormControl, Validators } from '@angular/forms';
import { AuthService } from '../../servicios/auth.service';
import { Usuario } from '../../modelos/usuario-interface';
import { Rol } from '../../modelos/rol-interface';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-editar-usuarios',
  standalone: true,
  imports: [RouterModule, MenuComponent, FormsModule, ReactiveFormsModule, CommonModule],
  templateUrl: './editar-usuarios.component.html',
  styleUrl: './editar-usuarios.component.css'
})
export class EditarUsuariosComponent implements OnInit {
  usuarioForm!: FormGroup;
  errorMensaje: string | null = null;
  idUsuario!: number;
  roles: any[] = [];

  constructor(private formBuilder: FormBuilder, private authService: AuthService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
      console.log('Componente cargado editar usuario');
      this.idUsuario = +this.route.snapshot.paramMap.get('idUsuario')!;
      console.log('ID Usuario: ', this.idUsuario);
      // this.listaRoles();
      // this.inicializarFormulario();
      // this.cargarDetalleUsuario();
      this.listaRoles(() => this.cargarDetalleUsuario());
      this.inicializarFormulario();
  }
  inicializarFormulario():void {
    this.usuarioForm = new FormGroup(
          {
            usuario: new FormControl('', Validators.required),
            correo: new FormControl('', [Validators.required, Validators.email]),
            password: new FormControl('', Validators.required),
            nombre: new FormControl('', Validators.required),
            apellidos: new FormControl('', Validators.required),
            rol: new FormControl('', Validators.required)
          });
  }
  detalleUsuario(): Observable<Usuario> {
    return this.authService.detalleUsuario(this.idUsuario);
  }
  cargarDetalleUsuario():void {
    this.detalleUsuario().subscribe({
      next: (response) => {
        console.log('Usuario detalle: ', response);
        console.log('rol-response', response.rol);
        console.log(typeof response.rol);

         if(typeof response.rol === 'object' && response.rol !== null) {
          const rol: Rol = response.rol as Rol;
          console.log(rol.idRol);
          console.log(rol.nombre);
          console.log(rol.descripcion);
        const usuarioSinPassword = {
          ...response,
          password: '',
          rol: {
            idRol: rol.idRol,
            nombre: rol.nombre,
            descripcion: rol.descripcion
          },
          idRol: rol.idRol
        }
        this.usuarioForm.patchValue(usuarioSinPassword);
      }
      },
      error:(error) => {
        console.error('Error al editar el usuario', error);
        this.errorMensaje = 'No se ha podido cargar el detalle del usuario';
      },
    });
  }

  actualizarUsuario():void {
    if(this.usuarioForm.valid) {
      const usuarioActualizado = this.usuarioForm.value;

      this.authService.actualizarUsuario(this.idUsuario, usuarioActualizado).subscribe(
        // ()=> {
        //   this.router.navigate(['/listaUsuarios']);
        // },
        // (error) => {
        //   this.errorMensaje = 'No se ha podido editar el usuario';
        // });
        {
          next: (response)=> {
            console.log('Usuario actualizado: ', response);
            this.router.navigate(['/listaUsuarios']);
          },
          error: (error) => {
            console.error('Error al actualizar el usuario: ', error);
            this.errorMensaje = 'No se ha podido actualizar el usuario';
          }
        }
      );
    }
  }

  listaRoles(callback?:()=>void): void {
    this.authService.getRoles().subscribe({
      next: (datos) => {
        this.roles = datos;
        console.log('Roles: ', datos);

        if(callback) {
          callback();
        }
      },
      error: (err) => {
        console.error('Error al obtener los roles:', err);
        this.errorMensaje = 'No se ha podido cargar la lista de roles';
      }
    })
  }
}
