import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../servicios/auth.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-listar-incidencia',
  standalone: true,
  imports: [],
  templateUrl: './listar-incidencia.component.html',
  styleUrl: './listar-incidencia.component.css'
})
export class ListarIncidenciaComponent implements OnInit {

  incidencias: any[] = [];
  errorMensaje: string = '';

  constructor(private authService: AuthService) {}




  listarIncidencias(): void {
    this.authService.getIncidencias().subscribe({
      next:(datos)=> {
        this.incidencias = datos;
        console.log('Incidencias: ', datos);
      },
      error:(error) => {
        console.error('Error al obtener las incidencias' , error);
        this.errorMensaje = 'No se ha podido cargar la lista de incidencias';
      }
    })
  }

}
