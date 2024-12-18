import { Routes } from '@angular/router';
import { LoginComponent } from './componentes/login/login.component';
import { InicioComponent } from './componentes/inicio/inicio.component';
import { ListaUsuariosComponent } from './componentes/lista-usuarios/lista-usuarios.component';
import { AuthGuard } from './guards/auth.guard';
import { CrearUsuariosComponent } from './componentes/crear-usuarios/crear-usuarios.component';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'inicio', component: InicioComponent },
  { path: 'listaUsuarios', component: ListaUsuariosComponent, canActivate: [AuthGuard] },
  { path: 'crearUsuarios', component: CrearUsuariosComponent, canActivate: [AuthGuard] },
  { path: '', redirectTo: '/login', pathMatch: 'full' }
];
