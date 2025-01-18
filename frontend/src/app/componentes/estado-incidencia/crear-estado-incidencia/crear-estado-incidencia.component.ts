import { Component, OnInit } from '@angular/core';
import { MenuComponent } from "../../menu/menu.component";
import { CommonModule } from '@angular/common';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { EstadoIncidencia } from '../../../modelos/estado-incidencia-interface';
import { AuthService } from '../../../servicios/auth.service';

@Component({
  selector: 'app-crear-estado-incidencia',
  standalone: true,
  imports: [MenuComponent, CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './crear-estado-incidencia.component.html',
  styleUrl: './crear-estado-incidencia.component.css'
})
export class CrearEstadoIncidenciaComponent implements OnInit {

  errorMensaje: string = '';
  estadoIncidencia: EstadoIncidencia = {
    idEstadoIncidencia:0,
    codEstado: '',
    descripcion: ''
  }

  estadoIncidenciaForm: FormGroup;

  constructor(private authService: AuthService) {
    this.estadoIncidenciaForm = new FormGroup({
      codEstado: new FormControl('', Validators.required),
      descripcion: new FormControl('', Validators.required)
    });
  }

  ngOnInit():void {
    console.log('Componente cargado crear Estado de Incidencia');
  }

  crearEstadoIncidencia(): void {
    if(this.estadoIncidenciaForm.valid) {
      this.authService.crearEstadoIncidencia(this.estadoIncidenciaForm.value).subscribe({
        next:(response) => {
          console.log('Estado Incidencia creada correctamente', response);
          this.estadoIncidenciaForm.reset();
          this.errorMensaje = 'Estado Incidencia creado correctamente';
          setTimeout(() => {
            this.errorMensaje = '';
          }, 3000);
        },
        error: (error) => {
          console.error('Error al crear el estado de incidencia:', error);
          this.errorMensaje = 'No se ha podido crear el estado de incidencia';
        }
      })
    }
  }

}
