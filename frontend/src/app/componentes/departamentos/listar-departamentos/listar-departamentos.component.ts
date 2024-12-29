import { Component, OnInit } from '@angular/core';
import { MenuComponent } from "../../menu/menu.component";
import { AuthService } from '../../../servicios/auth.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ConfirmarEliminarComponent } from '../../confirmar-eliminar/confirmar-eliminar.component';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-listar-departamentos',
  standalone: true,
  imports: [MenuComponent, CommonModule, RouterModule],
  templateUrl: './listar-departamentos.component.html',
  styleUrl: './listar-departamentos.component.css'
})
export class ListarDepartamentosComponent implements OnInit {

  departamentos: any[] = [];
  errorMensaje: string = '';

  constructor(private authService: AuthService, private modalService: NgbModal) {}

  ngOnInit(): void {
    this.listaDepartamentos();
  }

  listaDepartamentos(): void {
    this.authService.getDepartamentos().subscribe({
      next:(datos)=> {
        this.departamentos = datos;
        console.log('Departamentos: ', datos);
      },
      error:(err) => {
        console.error('Error al obtener los departamentos: ', err);
        this.errorMensaje = 'No se ha podido cargar la lista de departamentos';
      }
    })
  }

  abrirModal(idDepartamento: number) {
    console.log('Departamento seleccionado: ', idDepartamento);
    const modalRef = this.modalService.open(ConfirmarEliminarComponent);
    modalRef.result.then((result)=> {
      console.log('Dentro del modal Departamento: ', result);
      this.authService.eliminarDepartamento(idDepartamento).subscribe({
        next:()=> {
          console.log('Departamento eliminado correctamente');
          this.listaDepartamentos();
        },
        error:(err) => {
          console.error('Error al eliminar el departamento: ', err);
          this.errorMensaje = 'No se ha podido eliminar el departamento';
        }
      })
    })
  }

}
