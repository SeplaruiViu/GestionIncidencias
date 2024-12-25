import { Component, OnInit } from '@angular/core';
import { MenuComponent } from '../../menu/menu.component';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../../servicios/auth.service';

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

  constructor(private authService: AuthService) {}

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
}
