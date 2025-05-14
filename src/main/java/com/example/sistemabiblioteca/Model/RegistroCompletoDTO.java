package com.example.sistemabiblioteca.Model;

import java.sql.Date;
import java.util.Set;

public class RegistroCompletoDTO {
    // Datos para UserEntity (usuarios_registrados)
    private String nombreUsuario;
    private String contrasena;
    private Set<Long> rolesIds; // IDs de los roles seleccionados

    // Datos para UsuarioModel (usuario)
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String email;
    private String telefono;
    private String tipo; // "ESTUDIANTE", "DOCENTE", etc.
    private String direccion;
    private Date fechaRegistro;


    //setters y getters

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

     public Set<Long> getRolesIds() {
        return rolesIds;
    }

    public void setRolesIds(Set<Long> rolesIds) {
        this.rolesIds = rolesIds;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

      public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }


    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

     public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fecharegistro) {
        this.fechaRegistro = fecharegistro;
    }
}
