import { Routes } from '@angular/router';
import { LoginComponent } from './componentes/login/login.component';
import { InicioComponent } from './componentes/inicio/inicio.component';
import { ListaUsuariosComponent } from './componentes/lista-usuarios/lista-usuarios.component';
import { AuthGuard } from './guards/auth.guard';
import { CrearUsuariosComponent } from './componentes/crear-usuarios/crear-usuarios.component';
import { EditarUsuariosComponent } from './componentes/editar-usuarios/editar-usuarios.component';
import { ListarRolesComponent } from './componentes/roles/listar-roles/listar-roles.component';
import { CrearRolesComponent } from './componentes/roles/crear-roles/crear-roles.component';
import { EditarRolesComponent } from './componentes/roles/editar-roles/editar-roles.component';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'inicio', component: InicioComponent },
  { path: 'listaUsuarios', component: ListaUsuariosComponent, canActivate: [AuthGuard] },
  { path: 'crearUsuarios', component: CrearUsuariosComponent, canActivate: [AuthGuard] },
  { path: 'editarUsuario/:idUsuario', component: EditarUsuariosComponent, canActivate: [AuthGuard] },
  { path: 'listaRoles', component: ListarRolesComponent, canActivate: [AuthGuard] },
  { path: 'crearRoles', component: CrearRolesComponent, canActivate: [AuthGuard] },
  { path: 'editarRoles/:idRol', component: EditarRolesComponent, canActivate: [AuthGuard] },
  { path: '', redirectTo: '/login', pathMatch: 'full' }
];
