import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { guardGuard } from './auth/guard.guard';
import { LayoutComponent } from './layout/layout.component';
import { AgregarUsuariosComponent } from './agregar-usuarios/agregar-usuarios.component';
import { ListaUsuariosComponent } from './lista-usuarios/lista-usuarios.component';
import { UsuarioDetallesComponent } from './usuario-detalles/usuario-detalles.component';
import { ActualizarUsuariosComponent } from './actualizar-usuarios/actualizar-usuarios.component';
import { CrearRolComponent } from './crear-rol/crear-rol.component';
import { ListaIdiomasComponent } from './lista-idiomas/lista-idiomas.component';
import { AgregarIdiomasComponent } from './agregar-idiomas/agregar-idiomas.component';
import { ActualizarIdiomaComponent } from './actualizar-idioma/actualizar-idioma.component';
import { IdiomaDetallesComponent } from './idioma-detalles/idioma-detalles.component';
import { ListaMaterialesComponent } from './lista-materiales/lista-materiales.component';
import { AgregarMaterialesComponent } from './agregar-materiales/agregar-materiales.component';
import { ActualizarMaterialesComponent } from './actualizar-materiales/actualizar-materiales.component';

const routes: Routes = [
  {
    path: '',
    component: LayoutComponent,
    canActivate: [guardGuard],
    children: [
      { path: 'home', component: HomeComponent },
      { path: 'add_user', component: AgregarUsuariosComponent },
      { path: 'list_users', component: ListaUsuariosComponent },
      { path: 'details_user/:id', component: UsuarioDetallesComponent },
      { path: 'update_user/:id', component: ActualizarUsuariosComponent },
      { path: 'add_rol', component: CrearRolComponent },
      {path: 'idiomas', component: ListaIdiomasComponent},
      {path: 'add_idioma', component: AgregarIdiomasComponent},
      {path: 'update_idioma/:id', component: ActualizarIdiomaComponent},
      {path: 'details_idioma/:id', component: IdiomaDetallesComponent},
      {path: 'list_materiales',component:ListaMaterialesComponent},
      {path: 'add_material',component:AgregarMaterialesComponent},
      {path: 'update_material/:id', component:ActualizarMaterialesComponent},
      { path: '', redirectTo: 'home', pathMatch: 'full' }
    ]
  },
  { path: 'login', component: LoginComponent },
  { path: '**', redirectTo: 'login' } // manejo de rutas no válidas
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
