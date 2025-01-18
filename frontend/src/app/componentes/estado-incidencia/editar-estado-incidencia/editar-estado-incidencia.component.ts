import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../../servicios/auth.service';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { EstadoIncidencia } from '../../../modelos/estado-incidencia-interface';
import { Observable } from 'rxjs';
import { MenuComponent } from "../../menu/menu.component";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-editar-estado-incidencia',
  standalone: true,
  imports: [MenuComponent, CommonModule, FormsModule, ReactiveFormsModule, RouterModule],
  templateUrl: './editar-estado-incidencia.component.html',
  styleUrl: './editar-estado-incidencia.component.css'
})
export class EditarEstadoIncidenciaComponent implements OnInit {

  estadoIncidenciaForm!: FormGroup;
  errorMensaje: string | null = null;
  idEstado!: number;

  constructor(private authService: AuthService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.idEstado = +this.route.snapshot.paramMap.get('idEstado')!;
    this.cargarDetalleEstadoIncidencia();
    this.inicializarFormulario();
  }

  inicializarFormulario(): void {
    this.estadoIncidenciaForm = new FormGroup({
      codEstado: new FormControl('', Validators.required),
      descripcion: new FormControl('', Validators.required)
    });
  }

  detalleEstadoIncidencia(): Observable<EstadoIncidencia> {
    return this.authService.detalleEstadoIncidencia(this.idEstado);
  }

  cargarDetalleEstadoIncidencia(): void {
    this.detalleEstadoIncidencia().subscribe({
      next:(response) => {
        console.log('Estado incidencia detalle', response);
        this.estadoIncidenciaForm.patchValue(response);
      },
      error: (error) => {
        console.error('Error al cargar el detalle del estado incidencia', error);
        this.errorMensaje = 'No se ha podido cargar el detalle del estado incidencia';
      }
    })
  }

  actualizarEstadoIncidencia(): void {
    if(this.estadoIncidenciaForm.valid) {
      const estadoIncidenciaActualizado = this.estadoIncidenciaForm.value;

      this.authService.actualizarEstadoIncidencia(this.idEstado, estadoIncidenciaActualizado).subscribe({
        next: (response) => {
          console.log('Estado incidencia actualizado: ', response);
          this.router.navigate(['listaEstadosIncidencia']);
        },
        error:(error) => {
          console.error('Error al actualizar el estado incidencia: ', error);
          this.errorMensaje = 'No se ha podido actualizar el estado incidencia';
        }
      })
    }
  }
}
