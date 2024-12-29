import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MenuComponent } from '../../menu/menu.component';
import { Rol } from '../../../modelos/rol-interface';
import { FormGroup, FormsModule, NgForm, ReactiveFormsModule, FormControl, Validators } from '@angular/forms';
import { AuthService } from '../../../servicios/auth.service';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-crear-roles',
  standalone: true,
  imports: [CommonModule, MenuComponent, ReactiveFormsModule, RouterModule],
  templateUrl: './crear-roles.component.html',
  styleUrl: './crear-roles.component.css'
})
export class CrearRolesComponent implements OnInit {

  errorMensaje: string = '';
  rol: Rol = {
    idRol: 0,
    nombre: '',
    descripcion: ''
  }

  rolForm: FormGroup;

  ngOnInit(): void {
    console.log('componente cargado crear Rol');
  }

  constructor(private authService: AuthService) {
    this.rolForm = new FormGroup({
      nombre: new FormControl('', Validators.required),
      descripcion: new FormControl('', Validators.required)
    });
  }

  crearRol(): void {
    console.log('Datos del formulario crear Rol: ', this.rolForm.value);

    if (this.rolForm.valid) {
      this.authService.crearRol(this.rolForm.value).subscribe({
        next: (response) => {
          console.log('Rol creado correctamente', response);
          this.rolForm.reset();
          this.errorMensaje = 'Rol creado correctamente';
          setTimeout(() => {
            this.errorMensaje = '';
          }, 3000);
        },
        error: (error) => {
          console.error('Error al crear el rol:', error);
          this.errorMensaje = 'No se ha podido creare el rol';
        }
      });
    }
  }
}
