package com.example.sistemabiblioteca.Controllers;


import java.util.Optional;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.sistemabiblioteca.Service.UsuarioService;
import com.example.sistemabiblioteca.persistence.entity.CreateRoleRequest;
import com.example.sistemabiblioteca.persistence.entity.PermissionsEntity;
import com.example.sistemabiblioteca.persistence.entity.RoleEntity;
import com.example.sistemabiblioteca.persistence.entity.UserEntity;


@RequestMapping("/user")
@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {
     private final UsuarioService service;
    

    public UsuarioController(UsuarioService service) {
        this.service = service;
        
    }

    @GetMapping("/home")
    @ResponseBody
    public String home(){
        return "hola desde controlador usuario";
    }

    //este metodo sirve para ver todos los usuarios registrados
    @GetMapping("/all")
    @ResponseBody
    public Iterable<UserEntity> getAllUsers() {
        return service.obtenerUsuarios();
    }

    @GetMapping("/rol")
    @ResponseBody
     public Optional<UserEntity> getUser(@RequestParam String username) {
        return service.getUserByUsernameAndRole(username);
    }

    @GetMapping("/roles")
    @ResponseBody
    public Iterable<RoleEntity> getAllRol(){
                return service.getAllRoles();
    }

    @GetMapping("/permisos")
    @ResponseBody
    public Iterable<PermissionsEntity> getAllPermissions(){
                return service.getAllPermissions();
    }

      @PostMapping("/registrar")
    public ResponseEntity<UserEntity> addUser(@RequestBody UserEntity userEntity){
    
        UserEntity savedUser = service.guardarUsuario(userEntity);
        return ResponseEntity.ok(savedUser);
    } 
    
    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserId(@PathVariable Long id){
        
        UserEntity userFindId = service.obtenerUsuarioId(id);
        return ResponseEntity.ok(userFindId);
    }
     @PutMapping("/{id}")
    public ResponseEntity<UserEntity> upDateUser(@PathVariable Long id, @RequestBody UserEntity userEntity){
        
       
       UserEntity userUpdated = service.actualizarUsuario(id, userEntity);
        return ResponseEntity.ok(userUpdated);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        service.eliminarUsuario(id); // Método que debes tener en el servicio
        return ResponseEntity.noContent().build(); // HTTP 204 sin contenido
    }
    
    @PostMapping("/add_rol")
    @ResponseBody
    public ResponseEntity<String> addRol(@RequestBody CreateRoleRequest roleRequest){
        service.guardarRol(roleRequest);
        return ResponseEntity.ok("Rol creado con exito");
    }
    
}
