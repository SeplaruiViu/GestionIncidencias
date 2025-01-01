import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../../servicios/auth.service';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { Tecnico } from '../../../modelos/tecnico-interface';
import { Observable } from 'rxjs';
import { Departamento } from '../../../modelos/departamento-interface';
import { MenuComponent } from "../../menu/menu.component";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-editar-tecnicos',
  standalone: true,
  imports: [MenuComponent, RouterModule, CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './editar-tecnicos.component.html',
  styleUrl: './editar-tecnicos.component.css'
})
export class EditarTecnicosComponent implements OnInit {

  tecnicoForm!: FormGroup;
  errorMensaje: string | null = null;
  idTecnico!: number;
  departamentos: any[] = [];

  constructor(private formBuilder: FormBuilder, private authService: AuthService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    console.log('Componente cargado editar técnico');
    this.idTecnico = +this.route.snapshot.paramMap.get('idTecnico')!;
    console.log('ID Técnico: ', this.idTecnico);
    this.listaDepartamentos(() => this.cargarDetalleTecnico());
    this.inicializarFormulario();
  }

  inicializarFormulario(): void {
    this.tecnicoForm = new FormGroup({
      nombre: new FormControl('', Validators.required),
      apellidos: new FormControl('', Validators.required),
      departamento: new FormControl('', Validators.required)
    });
  }
  detalleTecnico(): Observable<Tecnico> {
    return this.authService.detalleTecnico(this.idTecnico);
  }

  cargarDetalleTecnico(): void {
    this.detalleTecnico().subscribe({
      next: (response) => {
        console.log('Técnico detalle: ', response);
        console.log('departamento-response', response.departamento);

        if (typeof response.departamento === 'object' && response.departamento !== null) {
          const departamento: Departamento = response.departamento as Departamento;
          console.log(departamento.idDepartamento);
          console.log(departamento.descripcion);
          console.log(departamento.codDepartamento);

          this.tecnicoForm.setValue({
            nombre: response.nombre,
            apellidos: response.apellidos,
            departamento: departamento.idDepartamento
          });
        }
      },
      error: (error) => {
        console.log('Error al cargar el detalle dle técnico: ', error);
        this.errorMensaje = 'No se ha podido cargar el detalle del técnico';
      }
    });
  }

  actualizarTecnico(): void {
    //original
    if(this.tecnicoForm.valid) {
      const tecnicoActualizado = this.tecnicoForm.value;
      this.authService.actualizarTecnico(this.idTecnico, tecnicoActualizado).subscribe(
        {
          next: (response) => {
            console.log('Técnico actualizado: ', response);
            this.router.navigate(['/listaTecnicos']);
          },
          error: (error) => {
            console.error('Error al actualizar el técnico: ', error);
            this.errorMensaje = 'No se ha podido actualizar el técnico';
          }
        }
      );
    }
  }

  listaDepartamentos(callback?: () => void): void {
    this.authService.getDepartamentos().subscribe({
      next: (datos) => {
        this.departamentos = datos;
        console.log('Departamentos: ', datos);

        if (callback) {
          callback();
        }
      },
      error: (error) => {
        console.error('Error al obtener los departamentos', error);
        this.errorMensaje = 'No se ha podido cargar la lista de departamentos';
      }
    })
  }
}
