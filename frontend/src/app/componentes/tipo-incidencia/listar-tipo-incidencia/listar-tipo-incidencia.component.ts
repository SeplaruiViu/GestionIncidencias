import { Component } from '@angular/core';
import { MenuComponent } from '../../menu/menu.component';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AuthService } from '../../../servicios/auth.service';
import { ConfirmarEliminarComponent } from '../../confirmar-eliminar/confirmar-eliminar.component';

@Component({
  selector: 'app-listar-tipo-incidencia',
  standalone: true,
  imports: [MenuComponent, CommonModule, RouterModule],
  templateUrl: './listar-tipo-incidencia.component.html',
  styleUrl: './listar-tipo-incidencia.component.css'
})
export class ListarTipoIncidenciaComponent {

  tiposIncidencia: any[] = [];
  errorMensaje: string = '';

  constructor(private authService: AuthService, private modalService: NgbModal) {}

  ngOnInit():void {
    this.listaTipoIncidencia();
  }

  listaTipoIncidencia(): void {
    this.authService.getTipoIncidencias().subscribe({
      next: (datos) => {
        this.tiposIncidencia = datos;
        console.log('Tipos de Incidencias: ', datos);
      },
      error: (error) => {
        console.error('Error al obtener los tipos de incidencia', error);
        this.errorMensaje = 'No se ha podido cargar la lista de tipos de incidencia';
      }
    })
  }

  abrirModal(idTipoIncidencia: number) {
    console.log('Tipo de incidencia seleccionado: ', idTipoIncidencia);
    const modalRef = this.modalService.open(ConfirmarEliminarComponent);
    modalRef.result.then((result) => {
      console.log('Dentro del modal Tipo de Incidencia: ', result);
      this.authService.eliminarTipoIncidencia(idTipoIncidencia).subscribe({
        next:() => {
          console.log('Tipo de incidencia eliminado correctamente');
          this.listaTipoIncidencia();
        },
        error:(error) => {
          console.error('Error al eliminar el tipo de Incidencia: ', error);
          this.errorMensaje = 'No se ha podido eliminar el tipo de incidencia';
        }
      })
    })
  }

}
