import { Component } from '@angular/core';
import { RestapiService } from '../restapi.service';
import {  Router } from '@angular/router';
import Swal from 'sweetalert2';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
@Component({
  selector: 'app-login',
  standalone: false,
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  username:string = '';
  password:string = '';
  message:string | null = null;

  constructor(private service:RestapiService, private router: Router, private http: HttpClient){}

  ngOnInit(){

  }

  doLogin(){
   let resp= this.service.login(this.username,this.password);
   resp.subscribe(data =>{
    
    console.log(data)
    localStorage.setItem('username', this.username);
    const headers = new HttpHeaders({
      'Authorization': 'Basic ' + btoa(this.username + ':' + this.password)  // Concatenamos el nombre de usuario y la contraseña en base64
    });
    
    this.router.navigate(['/home']);
    Swal.fire({
      position: "top-end",
      icon: "success",
      title: "Bienvenido " + this.username,
      showConfirmButton: false,
      timer: 1500
    });

   },
      err => {
        if (err.status === 401) {
          this.message = 'Usuario o contraseña incorrectos';
        } else {
          this.message = 'Error inesperado al iniciar sesión';
        }
    }
   )
  }

  
  
}
