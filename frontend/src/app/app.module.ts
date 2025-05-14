import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { RestapiService } from './restapi.service';
import { HttpClientModule } from '@angular/common/http';
import { MatOptionModule } from '@angular/material/core'; 
import { LayoutComponent } from './layout/layout.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { AgregarUsuariosComponent } from './agregar-usuarios/agregar-usuarios.component';
import { ListaUsuariosComponent } from './lista-usuarios/lista-usuarios.component';
import { ActualizarUsuariosComponent } from './actualizar-usuarios/actualizar-usuarios.component';
import { UsuarioDetallesComponent } from './usuario-detalles/usuario-detalles.component';
import { CrearRolComponent } from './crear-rol/crear-rol.component';
import { ListaIdiomasComponent } from './lista-idiomas/lista-idiomas.component';
import { AgregarIdiomasComponent } from './agregar-idiomas/agregar-idiomas.component';
import { ActualizarIdiomaComponent } from './actualizar-idioma/actualizar-idioma.component';
import { IdiomaDetallesComponent } from './idioma-detalles/idioma-detalles.component';
import { ListaMaterialesComponent } from './lista-materiales/lista-materiales.component';
import { AgregarMaterialesComponent } from './agregar-materiales/agregar-materiales.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { ActualizarMaterialesComponent } from './actualizar-materiales/actualizar-materiales.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    LayoutComponent,
    AgregarUsuariosComponent,
    ListaUsuariosComponent,
    ActualizarUsuariosComponent,
    UsuarioDetallesComponent,
    CrearRolComponent,
    ListaIdiomasComponent,
    AgregarIdiomasComponent,
    ActualizarIdiomaComponent,
    IdiomaDetallesComponent,
    ListaMaterialesComponent,
    AgregarMaterialesComponent,
    ActualizarMaterialesComponent,
  ],
  imports: [
    NgxPaginationModule,
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    MatFormFieldModule,
    MatSelectModule,
    MatOptionModule,
   ReactiveFormsModule,
  ],
  providers: [RestapiService],
  bootstrap: [AppComponent]
})
export class AppModule { }
