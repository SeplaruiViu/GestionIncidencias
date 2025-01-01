import { Component, OnInit } from '@angular/core';
import { MenuComponent } from '../../menu/menu.component';
import { Tecnico } from '../../../modelos/tecnico-interface';
import { Form, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../../servicios/auth.service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-crear-tecnicos',
  standalone: true,
  imports: [MenuComponent, FormsModule, ReactiveFormsModule, CommonModule, RouterModule],
  templateUrl: './crear-tecnicos.component.html',
  styleUrl: './crear-tecnicos.component.css'
})
export class CrearTecnicosComponent implements OnInit {

  departamentos: any[] = [];
  errorMensaje: string = '';
  tecnico: Tecnico = {
    nombre: '',
    apellidos: '',
    departamento: ''
  }

  tecnicoForm: FormGroup

  constructor(private authService: AuthService) {
    this.tecnicoForm = new FormGroup({
      nombre: new FormControl('', Validators.required),
      apellidos: new FormControl('', Validators.required),
      departamento: new FormControl('', Validators.required)
    });
  }
  ngOnInit(): void {
    console.log('Componente cargado crear técnicos');
    this.listaDepartamentos();
  }
  crearTecnico(): void {
    if (this.tecnicoForm.valid) {
      this.authService.crearTecnico(this.tecnicoForm.value).subscribe({
        next: (response) => {
          console.log('Tecnico creado correctamente', response);
          this.tecnicoForm.reset();
          this.errorMensaje = 'Tecnico creado correctamente';
          setTimeout(() => {
            this.errorMensaje = '';
          }, 3000);
        },
        error: (error) => {
          console.error('Error al crear el técnico', error);
          this.errorMensaje = 'No se ha podido crear el técnico';
        },
      });
    }
  }

  listaDepartamentos(): void {
    this.authService.getDepartamentos().subscribe({
      next:(datos) => {
        this.departamentos = datos;
        console.log('Departamentos: ', this.departamentos);
      },
      error: (error) => {
        console.error('Error al obtener los departamentos ', error);
        this.errorMensaje = 'No se ha podido cargar la lista de roles';
      }
    })
  }




}
