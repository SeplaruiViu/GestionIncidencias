import { Component, OnInit } from '@angular/core';
import { MenuComponent } from '../../menu/menu.component';
import { AuthService } from '../../../servicios/auth.service';
import { CommonModule } from '@angular/common';
import { NgbPagination, NgbPaginationConfig } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-listar-auditoria',
  standalone: true,
  imports: [MenuComponent, CommonModule, NgbPagination],
  templateUrl: './listar-auditoria.component.html',
  styleUrl: './listar-auditoria.component.css'
})
export class ListarAuditoriaComponent implements OnInit {

  constructor(private authService: AuthService, private paginationConfig: NgbPaginationConfig) { }
  auditorias: any[] = [];
  errorMensaje: string = '';
  items: any[] = [];
  pagedItems: any[] = [];
  currentPage: number = 1;
  pageSize: number = 10;


  ngOnInit(): void {
    this.listaAuditoria();
  }

  onPageChange(page:number):void {
    this.currentPage = page;
    this.updatePagedItems();
  }

  private updatePagedItems(): void {
    console.log(this.auditorias);
    const startIndex = (this.currentPage - 1) * this.pageSize;
    const endIndex = startIndex + this.pageSize;
    this.pagedItems = this.auditorias.slice(startIndex, endIndex);
  }
  listaAuditoria(): void {
    this.authService.getAuditoria().subscribe({
      next: (datos) => {
        this.auditorias = datos;
        console.log('Auditoria: ', datos);
        this.updatePagedItems();
      },
      error: (error) => {
        console.error('Error al recuperar la auditoria: ', error);
        this.errorMensaje = 'No se ha podido cargar la lista de auditoria';
      }
    })
  }

}
