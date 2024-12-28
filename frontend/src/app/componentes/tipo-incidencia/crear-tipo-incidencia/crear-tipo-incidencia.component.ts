import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormsModule, NgForm, ReactiveFormsModule, FormControl, Validators } from '@angular/forms';
import { MenuComponent } from '../../menu/menu.component';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../../servicios/auth.service';
import { TipoIncidencia } from '../../../modelos/tipo-incidencia-interface';

@Component({
  selector: 'app-crear-tipo-incidencia',
  standalone: true,
  imports: [CommonModule, MenuComponent, ReactiveFormsModule, RouterModule],
  templateUrl: './crear-tipo-incidencia.component.html',
  styleUrl: './crear-tipo-incidencia.component.css'
})
export class CrearTipoIncidenciaComponent implements OnInit {

  errorMensaje: string = '';
  tipoIncidencia: TipoIncidencia = {
    idTipoIncidencia: 0,
    codTipoIncidencia: '',
    descripcion: ''
  }
  tipoIncidenciaForm: FormGroup;

  constructor(private authService: AuthService) {
    this.tipoIncidenciaForm = new FormGroup({
      codTipoIncidencia: new FormControl('', Validators.required),
      descripcion: new FormControl('', Validators.required)
    });
  }


  ngOnInit(): void {
    console.log('Componente cargado crear Tipo Incidencia');
  }

  crearTipoIncidencia(): void {
    console.log('Datos del formulario crear Tipo Incidencia', this.tipoIncidenciaForm.value);

    if (this.tipoIncidenciaForm.valid) {
      this.authService.crearTipoIncidencia(this.tipoIncidenciaForm.value).subscribe({
        next: (response) => {
          console.log('Tipo Incidencia creado correctamente', response);
          this.tipoIncidenciaForm.reset();
          this.errorMensaje = 'Tipo Incidencia creado correctamente';
          setTimeout(() => {
            this.errorMensaje = '';
          }, 3000);
        },
        error: (error) => {
          console.error('Error al crear el tipo de incidencia:', error);
          this.errorMensaje = 'No se ha podido crear el tipo de incidencia';
        }
      })
    }
  }
}
