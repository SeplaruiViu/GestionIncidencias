import { Routes } from '@angular/router';
import { LoginComponent } from './componentes/login/login.component';
import { InicioComponent } from './componentes/inicio/inicio.component';
import { ListaUsuariosComponent } from './componentes/lista-usuarios/lista-usuarios.component';
import { AuthGuard } from './guards/auth.guard';

export const routes: Routes = [
  {path:'login', component: LoginComponent},
  {path:'inicio', component: InicioComponent},
  {path:'listaUsuarios', component:ListaUsuariosComponent, canActivate:[AuthGuard]},
  {path:'', redirectTo:'/login', pathMatch:'full'}
];
