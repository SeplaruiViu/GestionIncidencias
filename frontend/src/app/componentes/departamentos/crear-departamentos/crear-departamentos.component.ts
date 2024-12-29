import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../servicios/auth.service';
import { FormGroup, Validators, FormControl, ReactiveFormsModule } from '@angular/forms';
import { Departamento } from '../../../modelos/departamento-interface';
import { CommonModule } from '@angular/common';
import { MenuComponent } from '../../menu/menu.component';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-crear-departamentos',
  standalone: true,
  imports: [CommonModule, MenuComponent, ReactiveFormsModule, RouterModule],
  templateUrl: './crear-departamentos.component.html',
  styleUrl: './crear-departamentos.component.css'
})
export class CrearDepartamentosComponent implements OnInit {

  errorMensaje: string = '';
  departamento:Departamento = {
    idDepartamento:0,
    codDepartamento:'',
    descripcion:''
  }

  departamentoForm: FormGroup;

  ngOnInit(): void {
    console.log('Componente cargado crear Departamento');
  }

  constructor(private authService: AuthService) {
    this.departamentoForm = new FormGroup({
      codDepartamento: new FormControl('', Validators.required),
      descripcion: new FormControl('', Validators.required)
    });
  }

  crearDepartamento(): void {
    console.log('Datos del formulario crear Departamento: ', this.departamentoForm.value);

    if(this.departamentoForm.valid) {
      this.authService.crearDepartamento(this.departamentoForm.value).subscribe({
        next: (response) => {
          console.log('Departamento creado correctamente', response);
          this.departamentoForm.reset();
          this.errorMensaje = 'Departamento creado correctamente';
          setTimeout(()=> {
            this.errorMensaje = '';
          }, 3000);
        },
        error: (error) => {
          console.error('Error al crear el departamento: ', error);
          this.errorMensaje = 'No se ha podido crear el departamento';
        }
      });
    }

  }
}
