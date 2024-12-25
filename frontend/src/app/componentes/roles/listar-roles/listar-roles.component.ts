import { Component, OnInit } from '@angular/core';
import { MenuComponent } from '../../menu/menu.component';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../../servicios/auth.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ConfirmarEliminarComponent } from '../../confirmar-eliminar/confirmar-eliminar.component';


@Component({
  selector: 'app-listar-roles',
  standalone: true,
  imports: [MenuComponent, CommonModule],
  templateUrl: './listar-roles.component.html',
  styleUrl: './listar-roles.component.css'
})
export class ListarRolesComponent implements OnInit {
  roles: any[] = [];
  errorMensaje: string = '';

  constructor(private authService: AuthService, private modalService: NgbModal) {}

  ngOnInit():void {
    this.listaRoles();
  }
  listaRoles(): void {
    this.authService.getRoles().subscribe({
      next: (datos) => {
        this.roles = datos;
        console.log('Roles: ', datos);
      },
      error: (err) => {
        console.error('Error al obtener los roles:', err);
        this.errorMensaje = 'No se ha podido cargar la lista de roles';
      }
    })
  }

  abrirModal(idRol: number) {
    console.log('Rol seleccionado: ', idRol);
    const modalRef = this.modalService.open(ConfirmarEliminarComponent);
    modalRef.result.then((result) => {
      console.log('Dentro del modal Rol: ', result);
      this.authService.eliminarRol(idRol).subscribe({
        next:()=> {
          console.log('Rol eliminado correctamente');
          this.listaRoles();
        },
        error:(err)=> {
          console.error('Error al eliminar el rol:', err);
          this.errorMensaje = 'No se ha podido eliminar el rol';
        }
      })
    })
  }
}
