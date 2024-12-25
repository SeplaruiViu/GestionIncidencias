import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { MenuComponent } from '../menu/menu.component';
import { FormGroup, FormsModule, NgForm, ReactiveFormsModule, FormControl, Validators } from '@angular/forms';
import { Usuario } from '../../modelos/usuario-interface';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../servicios/auth.service';

@Component({
  selector: 'app-crear-usuarios',
  standalone: true,
  imports: [RouterModule, MenuComponent, FormsModule, ReactiveFormsModule, CommonModule],
  templateUrl: './crear-usuarios.component.html',
  styleUrl: './crear-usuarios.component.css'
})
export class CrearUsuariosComponent implements OnInit{
  roles: any[] = [];
  errorMensaje: string = '';
  usuario: Usuario = {
    usuario: '',
    correo: '',
    password: '',
    nombre: '',
    apellidos: '',
    rol: ''
  }

  usuarioForm: FormGroup;

  ngOnInit(): void {
    console.log('componente cargado');
    this.listaRoles();
  }

  constructor(private authService: AuthService) {
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
  crearUsuario(): void {
    console.log('Datos del formulario: ', this.usuarioForm.value);


    if(this.usuarioForm.valid) {
      this.authService.crearUsuario(this.usuarioForm.value).subscribe({
        next: (response) => {
          console.log('Usuario creado correctamente', response);
          this.usuarioForm.reset();
          this.errorMensaje = 'Usuario creado correctamente';
          setTimeout(() => {
            this.errorMensaje = '';
          }, 3000);
        },
        error:(error) => {
          console.error('Error al crear el usuario', error);
          this.errorMensaje = 'No se ha podido crear el usuario';
        },
      });
    }
  }

  listaRoles(): void {
    this.authService.getRoles().subscribe({
      next: (datos) => {
        this.roles = datos;
        console.log('Roles: ', datos);
      },
      error: (err) => {
        console.error('Error al obtener los roles:', err);
        this.errorMensaje = 'No se ha podido cargar la lista de roles';
      }
    })
  }
}
