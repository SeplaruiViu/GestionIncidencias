import { Component } from '@angular/core';
import { MenuComponent } from '../../menu/menu.component';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../../servicios/auth.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ConfirmarEliminarComponent } from '../../confirmar-eliminar/confirmar-eliminar.component';

@Component({
  selector: 'app-listar-prioridad-incidencia',
  standalone: true,
  imports: [MenuComponent, CommonModule, RouterModule],
  templateUrl: './listar-prioridad-incidencia.component.html',
  styleUrl: './listar-prioridad-incidencia.component.css'
})
export class ListarPrioridadIncidenciaComponent {

  prioridadesIncidencia: any[] = [];
  errorMensaje: string = '';

  constructor(private authService: AuthService, private modalService: NgbModal) {}

  ngOnInit():void {
    this.listaPrioridadIncidencia();
  }

  listaPrioridadIncidencia(): void {
    this.authService.getPrioridadesIncidencia().subscribe({
      next: (datos) => {
        this.prioridadesIncidencia = datos;
        console.log('Prioridades de Incidencias: ', datos);
      },
      error: (error)=> {
        console.error('Error al obtener las prioridades de incidencia', error);
        this.errorMensaje = 'No se ha podido cargar la lista de prioridades de incidencia';
      }
    })
  }

  abrirModal(idPrioridadIncidencia: number) {
    console.log('Prioridad de incidencia seleccionada: ', idPrioridadIncidencia);
    const modalRef = this.modalService.open(ConfirmarEliminarComponent);
    modalRef.result.then((result)=> {
      console.log('Dentro del modal Prioridad de Incidencia: ', result);
      this.authService.deletePrioridadIncidencia(idPrioridadIncidencia).subscribe({
        next:() => {
          console.log('Prioridad de incidencia eliminada correctamente');
          this.listaPrioridadIncidencia();
        },
        error:(error) => {
          console.error('Error al eliminar la prioridad de incidencia: ', error);
          this.errorMensaje = 'No se ha podido eliminar la prioridad de incidencia';
        }
      })
    })
  }

}
