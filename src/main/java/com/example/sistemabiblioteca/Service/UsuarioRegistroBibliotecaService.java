package com.example.sistemabiblioteca.Service;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.sistemabiblioteca.Model.RegistroCompletoDTO;
import com.example.sistemabiblioteca.Model.UsuarioModel;
import com.example.sistemabiblioteca.Repository.RoleRepository;
import com.example.sistemabiblioteca.Repository.UsuarioEntityRepository;
import com.example.sistemabiblioteca.Repository.UsuarioRepository;
import com.example.sistemabiblioteca.persistence.entity.RoleEntity;
import com.example.sistemabiblioteca.persistence.entity.UserEntity;



@Service
public class UsuarioRegistroBibliotecaService {

        private final UsuarioRepository usuariomodelRepository;
        private final RoleRepository roleRepository;
        private final UsuarioEntityRepository usuarioEntityRepository;

        //constructor
        public UsuarioRegistroBibliotecaService(
            UsuarioRepository usuariomodelRepository,
            RoleRepository roleRepository,
            UsuarioEntityRepository usuarioEntityRepository
        ){
            this.usuariomodelRepository = usuariomodelRepository;
            this.roleRepository = roleRepository;
            this.usuarioEntityRepository = usuarioEntityRepository;
        }

        //servicio para registrar un usuario de la bilbioteca
        public void RegisterUserBiblio(RegistroCompletoDTO dto){

            //variable para guardar el carnet generado 
            String carnet = generarCarnet(dto.getApellido1(), dto.getApellido2());

              // Crear entidad UsuarioModel
            UsuarioModel usuario = new UsuarioModel();
            usuario.setCarnet(carnet);
            usuario.setNombre(dto.getNombre());
            usuario.setApellido1(dto.getApellido1());
            usuario.setApellido2(dto.getApellido2());
            usuario.setEmail(dto.getEmail());
            usuario.setTelefono(dto.getTelefono());
            usuario.setTipo(dto.getTipo());
            usuario.setDireccion(dto.getDireccion());
            usuario.setFecha_registro(dto.getFechaRegistro());

            usuario = usuariomodelRepository.save(usuario);

             // Crear entidad UserEntity
            UserEntity user = new UserEntity();
            user.setUsername(carnet); // nombreUsuario será el carnet
            user.setPassword(dto.getContrasena());
            user.setEnabled(true);
            user.setAccountNoExpired(true);
            user.setAccountNolocked(true);
            user.setCredentialNoExpired(true);
           

            Set<RoleEntity> roles = dto.getRolesIds().stream()
                .map(id -> roleRepository.findById(id).orElseThrow())
                .collect(Collectors.toSet());

            user.setRoles(roles);

            user= usuarioEntityRepository.save(user);

        }


        //metodo para generar un carnet unico
        private String generarCarnet(String apellido1, String apellido2) {
            String a1 = (apellido1 == null || apellido1.isEmpty()) ? "X" : apellido1.substring(0, 1).toUpperCase();
            String a2 = (apellido2 == null || apellido2.isEmpty()) ? "X" : apellido2.substring(0, 1).toUpperCase();
            
            // Obtener los dos últimos dígitos del año actual
            String year = String.valueOf(LocalDate.now().getYear()).substring(2); // Ej. "2025" → "25"
            
            int secuencia = (int) (Math.random() * 900 + 100); // Número aleatorio de 3 cifras: 100-999

            return a1 + a2 + year + secuencia;
        }
}
