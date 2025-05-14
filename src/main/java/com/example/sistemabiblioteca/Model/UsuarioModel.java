package com.example.sistemabiblioteca.Model;

import java.sql.Date;

import com.example.sistemabiblioteca.persistence.entity.UserEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="USUARIO")
public class UsuarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDUSUARIO") 
    private Long idusuario;

    
    @Column(name = "CARNET", unique = true) 
    private String carnet;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "APELLIDO1")
    private String apellido1;

    @Column(name = "APELLIDO2")
    private String apellido2;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "TELEFONO")
    private String telefono;

      @Column(name = "TIPO")
    private String tipo;

     @Column(name = "DIRECCION")
    private String direccion;

    @Column(name = "FECHAREGISTRO")
    private Date fecha_registro;


    
}
