package com.example.sistemabiblioteca.Model;

import java.util.List;



public class UsuarioLoginDTO {
   

        private String nombre;
        private List<Long> idsRoles; // se usa para enviar solo el id del rol
        private String password;

        public UsuarioLoginDTO(String username, List<Long> roles) {
            this.nombre = username;
            this.idsRoles = roles;
        }
    
        
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Long> getIdsRoles() {
        return idsRoles;
    }

    public void setIdsRoles(List<Long> idsRoles) {
        this.idsRoles = idsRoles;
    }
      public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
