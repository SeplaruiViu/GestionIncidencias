import { Component, OnInit } from '@angular/core';
import { MenuComponent } from '../../menu/menu.component';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { AuthService } from '../../../servicios/auth.service';
import { Observable } from 'rxjs';
import { PrioridadIncidencia } from '../../../modelos/prioridad-incidencia-interface';

@Component({
  selector: 'app-editar-prioridad-incidencia',
  standalone: true,
  imports: [MenuComponent, FormsModule, CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './editar-prioridad-incidencia.component.html',
  styleUrl: './editar-prioridad-incidencia.component.css'
})
export class EditarPrioridadIncidenciaComponent implements OnInit {

  prioridadIncidenciaForm!: FormGroup;
  errorMensaje: string | null = null;
  idPrioridad!: number;

  constructor(private authService: AuthService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.idPrioridad = +this.route.snapshot.paramMap.get('idPrioridad')!;
    this.cargarDetallePrioridadIncidencia();
    this.inicializarFormulario();
  }

  inicializarFormulario(): void {
    this.prioridadIncidenciaForm = new FormGroup({
      nombre: new FormControl('', Validators.required),
      descripcion: new FormControl('', Validators.required)
    })
  }

  detallePrioridadIncidencia():Observable<PrioridadIncidencia> {
    return this.authService.detallePrioridadIncidencia(this.idPrioridad);
  }

  cargarDetallePrioridadIncidencia(): void {
    this.detallePrioridadIncidencia().subscribe({
      next: (response) => {
        console.log('Prioridad incidencia detalle: ', response);
        this.prioridadIncidenciaForm.patchValue(response);
      },
      error: (error) => {
        console.error('Error al cargar el detalle de la prioridad incidencia: ', error);
        this.errorMensaje = 'No se ha podido cargar el detalle de la prioridad incidencia';
      }
    })
  }

  actualizarPrioridadIncidencia(): void {
    if(this.prioridadIncidenciaForm.valid) {
      const prioridadIncidenciaActualizado = this.prioridadIncidenciaForm.value;

      this.authService.actualizarPrioridadIncidencia(this.idPrioridad, prioridadIncidenciaActualizado).subscribe({
        next: (response) => {
          console.log('Prioridad incidencia actualizada: ', response);
          this.router.navigate(['/listaPrioridadesIncidencia']);
        },
        error: (error) => {
          console.error('Error al actualizar la prioridad incidencia: ', error);
          this.errorMensaje = 'No se ha podido actualizar la prioridad incidencia';
        }
      })
    }
  }
}
