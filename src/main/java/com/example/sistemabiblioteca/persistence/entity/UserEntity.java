package com.example.sistemabiblioteca.persistence.entity;

import java.util.HashSet;
import java.util.Set;

import com.example.sistemabiblioteca.Model.UsuarioModel;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="USUARIOS_REGISTRADOS")
public class UserEntity {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="ID_REGISTRADO")
    private Long id;

    @Column(name ="NOMBREUSUARIO")
    private String username;

    @Column(name ="CONTRASENIA")
    private String password;

    @Column(name ="ISENABLED")
    private boolean isEnabled;

    @Column(name ="ACCOUNTNOEXPIRED")
    private boolean accountNoExpired;

    @Column(name ="ACCOUNTNOLOCKED")
    private boolean accountNolocked;

    @Column(name ="CREDENTIALNOEXPIRED")
    private boolean credentialNoExpired;

  

    @Builder.Default
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinTable(
        name = "USUARIOS_ROLES",
        joinColumns = @JoinColumn(name = "ID_REGISTRADO"),
        inverseJoinColumns = @JoinColumn(name = "ID_ROL")
    )
    private Set<RoleEntity> roles = new HashSet<>();

    

}
