import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../servicios/auth.service';
import { MenuComponent } from '../menu/menu.component';
import { CommonModule } from '@angular/common';
import { AuthGuard } from '../../guards/auth.guard';
import { ConfirmarEliminarComponent } from '../confirmar-eliminar/confirmar-eliminar.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-lista-usuarios',
  standalone: true,
  imports: [MenuComponent, CommonModule, ConfirmarEliminarComponent],
  templateUrl: './lista-usuarios.component.html',
  styleUrl: './lista-usuarios.component.css',
})
export class ListaUsuariosComponent {
  usuarios: any[] = [];
  errorMensaje: string = '';
  isDialogOpen = false;
  usuarioAEliminar: number | null = null;

  constructor(private authService: AuthService, private modalService: NgbModal) {}

  ngOnInit(): void {
    this.listaUsusarios();
  }
  listaUsusarios(): void {
    this.authService.getUsuarios().subscribe({
      next: (datos) => {
        this.usuarios = datos;
        console.log('Usuarios: ', datos);
      },
      error: (err) => {
        console.error('Error al obtener los usuarios:', err);
        this.errorMensaje = 'No se ha podido cargar la lista de usuarios';
      },
    });
  }

  confirmarEliminarUsuario(idUsuario: number): void {
    console.log('Usuario a eliminar: ', idUsuario);
    this.usuarioAEliminar = idUsuario;
    this.isDialogOpen = true;
    console.log('Estado de isDialogOpen:', this.isDialogOpen);
  }
  onConfirmarEliminarUsuario(result: boolean): void {
    console.log('Confirmación recibida: ', result);
    this.isDialogOpen = false;
    if (result && this.usuarioAEliminar !== null) {
      this.eliminarUsuario(this.usuarioAEliminar);
    }
  }

  abrirModal(idUsuario:number) {
    console.log('IDUSUARIO: ', idUsuario);
    const modalRef = this.modalService.open(ConfirmarEliminarComponent);
    modalRef.result.then((result)=> {
      console.log('Dentro del modal: ', result);
      // this.authService.deleteUsuario(idUsuario);
      // this.listaUsusarios();
      this.authService.deleteUsuario(idUsuario).subscribe({
        next:() => {
          console.log('Usuario eliminado correctamente ');
          this.listaUsusarios();
        },
        error:(err) => {
          console.error('Error al eliminar el usuario: ', err);
        }
      });

    }, (reason)=> {
      console.log('Dentro del modal: ', reason);
    });
  }
  eliminarUsuario(idUsuario: number) {}
}
