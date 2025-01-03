import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { MenuComponent } from '../../menu/menu.component';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-crear-prioridad-incidencia',
  standalone: true,
  imports: [CommonModule, MenuComponent, ReactiveFormsModule, RouterModule],
  templateUrl: './crear-prioridad-incidencia.component.html',
  styleUrl: './crear-prioridad-incidencia.component.css'
})
export class CrearPrioridadIncidenciaComponent {

}
