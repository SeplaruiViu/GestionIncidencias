import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, FormControl, Validators } from '@angular/forms';
import { MenuComponent } from '../../menu/menu.component';
import { CommonModule } from '@angular/common';
import { Observable } from 'rxjs';
import { AuthService } from '../../../servicios/auth.service';
import { TipoIncidencia } from '../../../modelos/tipo-incidencia-interface';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-editar-tipo-incidencia',
  standalone: true,
  imports: [MenuComponent, FormsModule, ReactiveFormsModule, CommonModule, RouterModule],
  templateUrl: './editar-tipo-incidencia.component.html',
  styleUrl: './editar-tipo-incidencia.component.css'
})
export class EditarTipoIncidenciaComponent implements OnInit {

  tipoIncidenciaForm!: FormGroup;
  errorMensaje: string | null = null;
  idTipoIncidencia!: number;

  constructor(private authService: AuthService, private route:ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.idTipoIncidencia = +this.route.snapshot.paramMap.get('idTipoIncidencia')!;
    this.cargarDetalleTipoIncidencia();
    this.inicializarFormulario();
  }

  inicializarFormulario(): void {
    this.tipoIncidenciaForm = new FormGroup(
      {
        codTipoIncidencia: new FormControl('', Validators.required),
        descripcion: new FormControl('', Validators.required)
      });
  }

  detalleTipoIncidencia(): Observable<TipoIncidencia> {
    return this.authService.detalleTipoIncidencia(this.idTipoIncidencia);
  }

  cargarDetalleTipoIncidencia(): void {
    this.detalleTipoIncidencia().subscribe({
      next: (response) => {
        console.log('Tipo incidencia detalle: ', response);
        this.tipoIncidenciaForm.patchValue(response);
      },
      error: (error) => {
        console.error('Error al cargar el detalle del tipo incidencia: ', error);
        this.errorMensaje = 'No se ha podido editar el tipo incidencia';
      }
    });
  }

  actualizarTipoIncidencia(): void {
    if(this.tipoIncidenciaForm.valid) {
      const tipoIncidenciaActualizado = this.tipoIncidenciaForm.value;

      this.authService.actualizarTipoIncidencia(this.idTipoIncidencia, tipoIncidenciaActualizado).subscribe({
        next: (response) => {
          console.log('Tipo incidencia actualizado: ', response);
          this.router.navigate(['/listaTiposIncidencia']);
        },
        error: (error) => {
          console.error('Error al actualizar el tipo incidencia: ', error);
          this.errorMensaje = 'No se ha podido actualizar el tipo incidencia';
        }
      });
    }
  }


}
