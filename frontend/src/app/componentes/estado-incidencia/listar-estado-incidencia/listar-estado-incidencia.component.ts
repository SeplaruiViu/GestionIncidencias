import { Component, OnInit } from '@angular/core';
import { MenuComponent } from '../../menu/menu.component';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../../servicios/auth.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ConfirmarEliminarComponent } from '../../confirmar-eliminar/confirmar-eliminar.component';

@Component({
  selector: 'app-listar-estado-incidencia',
  standalone: true,
  imports: [MenuComponent, CommonModule, RouterModule],
  templateUrl: './listar-estado-incidencia.component.html',
  styleUrl: './listar-estado-incidencia.component.css'
})
export class ListarEstadoIncidenciaComponent implements OnInit {

  estadosIncidencia: any[] = [];
  errorMensaje: string = '';

  constructor(private authService: AuthService, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.listarEstadoIncidencia();
  }

  listarEstadoIncidencia(): void {
    this.authService.getEstadosIncidencia().subscribe({
      next: (datos) => {
        this.estadosIncidencia = datos;
        console.log('Estados de Incidencias: ', datos);
      },
      error: (error) => {
        console.error('Error al obtener los estados de incidencia', error);
        this.errorMensaje = 'No se ha podido cargar la lista de estados de incidencia';
      }
    })
  }

  abrirModal(idEstadoIncidencia: number) {
    console.log('Estado de incidencia seleccionada: ', idEstadoIncidencia);
    const modalRef = this.modalService.open(ConfirmarEliminarComponent);
    modalRef.result.then((result) => {
      console.log('Dentro del modal Estado de Incidencia: ', result);
      this.authService.deleteEstadoIncidencia(idEstadoIncidencia).subscribe({
        next: () => {
          console.log('Estado de incidencia eliminada correctamente');
          this.listarEstadoIncidencia();
        },
        error: (error) => {
          console.error('Error al eliminar el estado de incidencia', error);
          this.errorMensaje = 'No se ha podido eliminar el estado de incidencia';
        }
      })
    })
  }
}
