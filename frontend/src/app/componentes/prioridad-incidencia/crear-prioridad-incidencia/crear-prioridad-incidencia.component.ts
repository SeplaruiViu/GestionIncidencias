import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MenuComponent } from '../../menu/menu.component';
import { FormGroup, ReactiveFormsModule, Validators, FormControl } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../../servicios/auth.service';
import { PrioridadIncidencia } from '../../../modelos/prioridad-incidencia-interface';

@Component({
  selector: 'app-crear-prioridad-incidencia',
  standalone: true,
  imports: [CommonModule, MenuComponent, ReactiveFormsModule, RouterModule],
  templateUrl: './crear-prioridad-incidencia.component.html',
  styleUrl: './crear-prioridad-incidencia.component.css'
})
export class CrearPrioridadIncidenciaComponent implements OnInit {

  errorMensaje: string = '';
  prioridadIncidencia: PrioridadIncidencia = {
    idPrioridad: 0,
    nombre: '',
    descripcion: ''
  }
  prioridadIncidenciaForm: FormGroup;

  constructor(private authService: AuthService) {
    this.prioridadIncidenciaForm = new FormGroup({
      nombre: new FormControl('', Validators.required),
      descripcion: new FormControl('', Validators.required)
    })
  }

  ngOnInit(): void {
    console.log('Componente cargado crear Prioridad Incidencia');
  }

  crearPrioridadIncidencia(): void {
    if (this.prioridadIncidenciaForm.valid) {
      this.authService.crearPrioridadIncidencia(this.prioridadIncidenciaForm.value).subscribe({
        next: (response) => {
          console.log('Prioridad Incidencia creada correctamente', response);
          this.prioridadIncidenciaForm.reset();
          this.errorMensaje = 'Prioridad Incidencia creada correctamente';
          setTimeout(() => {
            this.errorMensaje = '';
          }, 3000);
        },
        error: (error) => {
          console.error('Error al crear la prioridad incidencia', error);
          this.errorMensaje = 'No se ha podido crear la prioridad incidencia';
        }
      });
    }
  }
}
