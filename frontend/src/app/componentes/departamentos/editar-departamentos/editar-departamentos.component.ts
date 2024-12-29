import { Component, OnInit } from '@angular/core';
import { MenuComponent } from '../../menu/menu.component';
import { FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../../servicios/auth.service';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { Departamento } from '../../../modelos/departamento-interface';
import { Observable } from 'rxjs';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-editar-departamentos',
  standalone: true,
  imports: [CommonModule, MenuComponent,  FormsModule, ReactiveFormsModule, RouterModule],
  templateUrl: './editar-departamentos.component.html',
  styleUrl: './editar-departamentos.component.css'
})
export class EditarDepartamentosComponent implements OnInit {

  departamentoForm!: FormGroup;
  errorMensaje: string | null = null;
  idDepartamento!: number;


  constructor(private formBuilder: FormBuilder, private authService: AuthService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.idDepartamento = +this.route.snapshot.paramMap.get('idDepartamento')!;
    this.cargarDetalleDepartamento();
    this.inicializarFormulario();
  }

  inicializarFormulario(): void {
    this.departamentoForm = new FormGroup({
      codDepartamento: new FormControl('', Validators.required),
      descripcion: new FormControl('', Validators.required)
    });
  }

  detalleDepartamento(): Observable<Departamento> {
    return this.authService.detalleDepartamento(this.idDepartamento);
  }

  cargarDetalleDepartamento(): void {
    this.detalleDepartamento().subscribe({
      next:(response)=> {
        console.log('Departamento detalle: ', response);
        this.departamentoForm.patchValue(response);
      },
      error: (error) => {
        console.error('Error al cargar el detalle del departamento: ', error);
        this.errorMensaje = 'No se ha podido editar el departamento';
      }
    });
  }

  actualizarDepartamento(): void {
    if(this.departamentoForm.valid) {
      const departamentoActualizado = this.departamentoForm.value;

      this.authService.actualizarDepartamento(this.idDepartamento, departamentoActualizado).subscribe({
        next: (response) => {
          console.log('Departamento actualizado: ', response);
          this.router.navigate(['/listaDepartamentos']);
        },
        error: (error) => {
          console.error('Error al actualizar el departamento: ', error);
          this.errorMensaje = 'No se ha podido actualizar el departamento';
        }
      });
    }
  }

}
