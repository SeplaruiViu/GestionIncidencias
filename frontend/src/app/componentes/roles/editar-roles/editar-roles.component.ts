import { Component, OnInit } from '@angular/core';
import { MenuComponent } from '../../menu/menu.component';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { AuthService } from '../../../servicios/auth.service';
import { Observable } from 'rxjs';
import { Rol } from '../../../modelos/rol-interface';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-editar-roles',
  standalone: true,
  imports: [MenuComponent, FormsModule, ReactiveFormsModule, CommonModule],
  templateUrl: './editar-roles.component.html',
  styleUrl: './editar-roles.component.css'
})
export class EditarRolesComponent implements OnInit {

  rolForm!: FormGroup;
  errorMensaje: string | null = null;
  idRol!: number;

  constructor(private formBuilder: FormBuilder, private authService: AuthService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.idRol = +this.route.snapshot.paramMap.get('idRol')!;
    this.cargarDetalleRol();
    this.inicializarFormulario();
  }

  inicializarFormulario(): void {
    this.rolForm = new FormGroup(
      {
        nombre: new FormControl('', Validators.required),
        descripcion: new FormControl('', Validators.required)
      });
  }

  detalleRol(): Observable<Rol> {
    return this.authService.detalleRol(this.idRol);
  }

  cargarDetalleRol(): void {
    this.detalleRol().subscribe({
      next: (response) => {
        console.log('Rol detalle: ', response);
        this.rolForm.patchValue(response);
      },
      error: (error) => {
        console.error('Error al cargar el detalle del rol: ', error);
        this.errorMensaje = 'No se ha podido editar el rol';
      }
    });
  }

  actualizarRol():void {
    if(this.rolForm.valid) {
      const rolActualizado = this.rolForm.value;

      this.authService.actualizarRol(this.idRol, rolActualizado).subscribe({
        next: (response) => {
          console.log('Rol actualizado: ', response);
          this.router.navigate(['/listaRoles']);
        },
        error: (error) => {
          console.error('Error al actualizar el rol: ', error);
          this.errorMensaje = 'No se ha podido actualizar el rol';
        }
      });
    }
  }

}
