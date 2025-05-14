import { Component, OnInit } from '@angular/core';
import { Usuariologin } from '../usuariologin';
import { UserService } from '../user.service';
import { Rol } from '../rol';
import Swal from 'sweetalert2';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-lista-usuarios',
  standalone: false,
  templateUrl: './lista-usuarios.component.html',
  styleUrl: './lista-usuarios.component.css'
})
export class ListaUsuariosComponent implements OnInit{

  filtro: string = '';
  roles: any[] = [];
  usuariosLista: Usuariologin[];
  paginaActual: number = 0; // Página actual (comienza en 0)
  itemsPorPagina: number = 5; // Número de elementos por página
  usuariosFiltrados: Usuariologin[];
  usuarioActualizar: Usuariologin;
  usuariosrol: Usuariologin;
  

  usuarios = [
    { nombre: 'Juan Pérez', rol: 'Administrador' },
    { nombre: 'Ana Gómez', rol: 'Usuario' },
    { nombre: 'Carlos Ruiz', rol: 'Moderador' },
    { nombre: 'Laura Méndez', rol: 'Usuario' }
  ];

  constructor(private listauser: UserService,private usuarioServicio: UserService, private route:Router){
  
  }



  ngOnInit(): void {
     this.usuariosFiltradosFunc();
     this.obtenerUsuariosLista();

  }

  usuariosFiltradosFunc() {
   

    if (!this.filtro) {
      this.usuariosFiltrados = this.usuariosLista;
    } else {
      const texto = this.filtro.toLowerCase();
      this.usuariosFiltrados = this.usuariosLista.filter(usuario =>
        usuario.username?.toLowerCase().includes(texto) ||
        usuario.roles?.some(rol => rol.roleEnum.toLowerCase().includes(texto))
      );
    }
    this.paginaActual = 0; // Reinicia la página si se filtra
  }

  private obtenerUsuariosLista(){
    this.listauser.obtenerListaUsuarios().subscribe(dato =>{
      this.usuariosLista = dato;
      console.log('Usuarios cargados:', this.usuariosLista);
      this.usuariosFiltrados = this.usuariosLista;
    });
  }

  usuariosPorPagina() {
    const startIndex = this.paginaActual * this.itemsPorPagina;
    const endIndex = (this.paginaActual + 1) * this.itemsPorPagina;
    return this.usuariosFiltrados.slice(startIndex, endIndex); // Slice para cortar los elementos de 5 en 5
  }

   // Cambiar la página (anterior o siguiente)
   cambiarPagina(cambio: number) {
    const nuevaPagina = this.paginaActual + cambio;
    if (nuevaPagina >= 0 && nuevaPagina < this.totalPaginas()) {
      this.paginaActual = nuevaPagina;
    }
  }

  // Calcular el número total de páginas
  totalPaginas() {
    return Math.ceil(this.usuariosLista.length / this.itemsPorPagina);
  }

  eliminarUsuario(id:number){
    
     Swal.fire({
         title: "¿Deseas eliminar el usuario?",
         showDenyButton: true,
         showCancelButton: true,
         confirmButtonText: "Eliminar Usuario",
         denyButtonText: `No Eliminar`
       }).then((result) => {
         if (result.isConfirmed) {
           this.usuarioServicio.eliminarUsuario(id).subscribe({
             next: res => {
               console.log('Usuario registrado con éxito', res);
                this.obtenerUsuariosLista();
               Swal.fire("Eliminado con éxito!", "", "success");
             
             },
             error: err => {
               console.error('Error al registrar usuario', err);
               Swal.fire("Error al guardar", err.error.message || "Error desconocido", "error");
             }
           });
         } else if (result.isDenied) {
           Swal.fire("Cancelado!", "", "info");
         }
       });
     
   }
   verDetalles(id:number, username: string,role: string){
      this.route.navigate(['details_user', id], {
      queryParams: {
         role: role,
         username: username 
        }
    });
  }

   ActualizarUsuario(id:number, username: string,role: string){
      this.route.navigate(['update_user', id], {
      queryParams: {
         role: role,
         username: username 
        }
    });
  }
  }