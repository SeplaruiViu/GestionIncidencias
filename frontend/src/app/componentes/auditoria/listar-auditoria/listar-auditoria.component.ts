import { Component, OnInit } from '@angular/core';
import { MenuComponent } from '../../menu/menu.component';
import { AuthService } from '../../../servicios/auth.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-listar-auditoria',
  standalone: true,
  imports: [MenuComponent, CommonModule],
  templateUrl: './listar-auditoria.component.html',
  styleUrl: './listar-auditoria.component.css'
})
export class ListarAuditoriaComponent implements OnInit {

  constructor(private authService: AuthService) { }
  auditorias: any[] = [];
  errorMensaje: string = '';

  ngOnInit(): void {
    this.listaAuditoria();
  }

  listaAuditoria(): void {
    this.authService.getAuditoria().subscribe({
      next: (datos) => {
        this.auditorias = datos;
        console.log('Auditoria: ', datos);
      },
      error: (error) => {
        console.error('Error al recuperar la auditoria: ', error);
        this.errorMensaje = 'No se ha podido cargar la lista de auditoria';
      }
    })
  }

}
