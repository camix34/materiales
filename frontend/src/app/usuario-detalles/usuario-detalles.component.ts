import { Component, OnInit } from '@angular/core';
import { Usuariologin } from '../usuariologin';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../user.service';

@Component({
  selector: 'app-usuario-detalles',
  standalone: false,
  templateUrl: './usuario-detalles.component.html',
  styleUrl: './usuario-detalles.component.css'
})
export class UsuarioDetallesComponent implements OnInit{

  id:number;
  usuariologin:Usuariologin;
  usuario: Usuariologin | null = null;
  userId: number = 0;
  rolRecibido: string = '';
  usernameRecibido: string ='';

  constructor(private route:ActivatedRoute,private usuarioServicio: UserService, private router: Router){
    this.id = this.route.snapshot.params['id']; //sirve para capturar el id en la ruta
    this.usuariologin = new Usuariologin;
    this.usuarioServicio.obtenerUsuarioLoginID(this.id).subscribe(dato=>{
      this.usuariologin = dato;
    });
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.userId = +params.get('id')!;
    });

    // Obtener el rol desde queryParams
    this.route.queryParams.subscribe(params => {
      this.rolRecibido = params['role'];
      this.usernameRecibido = params['username']

    
    });

   
  }

  volver(){
    this.router.navigate(['/list_users']);
  }

}
