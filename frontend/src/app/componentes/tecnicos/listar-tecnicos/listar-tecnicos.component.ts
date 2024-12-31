import { Component, OnInit } from '@angular/core';
import { MenuComponent } from '../../menu/menu.component';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../../servicios/auth.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ConfirmarEliminarComponent } from '../../confirmar-eliminar/confirmar-eliminar.component';

@Component({
  selector: 'app-listar-tecnicos',
  standalone: true,
  imports: [MenuComponent, CommonModule, RouterModule],
  templateUrl: './listar-tecnicos.component.html',
  styleUrl: './listar-tecnicos.component.css'
})
export class ListarTecnicosComponent implements OnInit{

  tecnicos: any[] = [];
  errorMensaje: string = '';
  isDialogOpen = false;
  tecnicoAEliminar: number | null = null;

  constructor(private authService: AuthService, private modalService: NgbModal) {}

  ngOnInit(): void {
    this.listaTecnicos();
  }

  listaTecnicos(): void {
    this.authService.getTecnicos().subscribe({
      next:(datos) => {
        this.tecnicos = datos;
        console.log('Tecnicos: ', datos);
      },
      error: (err) => {
        console.error('Error al obtener los tecnicos: ', err);
        this.errorMensaje = 'No se ha podido cargar la lista de tecnicos';
      },
    });
  }

  abrirModal(idTecnico:number) {
    console.log('IDTECNICO: ', idTecnico);
    const modalRef = this.modalService.open(ConfirmarEliminarComponent);
    modalRef.result.then((result)=> {
      console.log('Dentro del modal: ', result);
      this.authService.deleteTecnico(idTecnico).subscribe({
        next:() => {
          console.log('Técnico eliminado correctamente ');
          this.listaTecnicos();
        },
        error: (err) => {
          console.error('Error al eliminar el técnico: ', err);
          this.errorMensaje = 'No se ha podido eliminar el técnico';
        }
      })
    })
  }

  // confirmarEliminarTecnico(idTecnico: number):void {
  //   console.log('Tecnico a eliminar: ¡', idTecnico);
  //   this.tecnicoAEliminar = idTecnico;
  //   this.isDialogOpen = true;
  // }

  // onConfirmarEliminarTecnico(result: boolean): void {
  //   console.log('Confirmación recibida: ', result);
  //   this.isDialogOpen = false;
  //   if(result && this.tecnicoAEliminar !== null) {
  //     this.eliminarT
  //   }
  // }

}
