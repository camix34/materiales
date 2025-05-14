import { Component } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import Swal from 'sweetalert2';
import { filter } from 'rxjs/operators';
import { RestapiService } from '../restapi.service';
import { AuthService } from '../auth/auth.service';
import { UserService } from '../user.service';

@Component({
  selector: 'app-layout',
  standalone: false,
  templateUrl: './layout.component.html',
  styleUrl: './layout.component.css'
})
export class LayoutComponent {
  currentRouteName: string = '';
  userRole: string = '';
  role: string | null = null;
  user: any;
  username: string = '';
  roleEnum: string = '';

  constructor(private router: Router,private authService: AuthService, private userService: UserService, private route: ActivatedRoute) {

    this.router.events
    .pipe(filter(event => event instanceof NavigationEnd))
    .subscribe((event: NavigationEnd) => {
      this.setRouteName(event.urlAfterRedirects);
    });
  }
  logout() {
    Swal.fire({
      title: '¿Cerrar sesión?',
      text: 'Se cerrará tu sesión actual',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sí, cerrar sesión',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
      if (result.isConfirmed) {
        localStorage.removeItem('username');
        this.router.navigate(['/login']);
        Swal.fire('Sesión cerrada', '', 'success');
      }
    });
  
  }

  ngOnInit(): void {
    this.authService.getUserRole().subscribe({
      next: (rol) => this.role = rol,
      error: () => this.role = null
    });

    const username = localStorage.getItem('username');

    if (username) {
      this.userService.getUserWithRole(username).subscribe({
        next: (res) => this.user = res,
        error: (err) => console.error('Error al cargar usuario:', err)
      });
    }
    console.log(this.role);

    
  
  }
  
 

  setRouteName(url: string) {
  if (url.startsWith('/details_user/')) {
    this.currentRouteName = 'Detalles del usuario';
    return;
  }
  else if (url.startsWith('/update_user/')) {
    this.currentRouteName = 'Actualizar usuario';
    return;
  }

    switch (url) {
      case '/home':
        this.currentRouteName = 'Dasboard';
        break;
      case '/add_user':
        this.currentRouteName = 'Agregar Usuarios';
        break;
      case '/list_users':
          this.currentRouteName = 'Lista de Usuarios Registrados';
          break;
      case '/details_user':
        this.currentRouteName = 'Detalles del usuario';
        break;
      case '/add_rol':
        this.currentRouteName = 'Agregar un Rol';
        break;
      default:
        this.currentRouteName = 'Dashboard';
        break;
    }
  }

}
