package com.example.sistemabiblioteca.Service;




import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.sistemabiblioteca.Repository.PermissionRepository;
import com.example.sistemabiblioteca.Repository.RoleRepository;
import com.example.sistemabiblioteca.Repository.UserRepositoryJPA;
import com.example.sistemabiblioteca.Repository.UsuarioEntityRepository;
import com.example.sistemabiblioteca.persistence.entity.CreateRoleRequest;
import com.example.sistemabiblioteca.persistence.entity.PermissionsEntity;
import com.example.sistemabiblioteca.persistence.entity.RoleEntity;
import com.example.sistemabiblioteca.persistence.entity.RoleEnum;
import com.example.sistemabiblioteca.persistence.entity.UserEntity;

@Service
public class UsuarioService {
    
 
     private final UsuarioEntityRepository repoentity;
     private final UserRepositoryJPA userRepositoryJPA;
     private final RoleRepository roleRepository;
     private final PermissionRepository permisosRepository;

   
    


    
    public UsuarioService(
        UsuarioEntityRepository repoentity,
         RoleRepository roleRepository, UserRepositoryJPA userRepositoryJPA,
         PermissionRepository permisosRepository
        
         ) {

       
        this.repoentity = repoentity;
        this.roleRepository = roleRepository;
        this.userRepositoryJPA = userRepositoryJPA;
        this.permisosRepository = permisosRepository;
      
      
    }

    public Iterable<UserEntity> obtenerUsuarios() {
        return repoentity.findAll();
    }

    public UserEntity guardarUsuario(UserEntity usuEntity){
        usuEntity.setEnabled(true);
        usuEntity.setAccountNoExpired(true);
        usuEntity.setAccountNolocked(true);
        usuEntity.setCredentialNoExpired(true);
        return repoentity.save(usuEntity);
    }

   
/* public UserEntity registrarDesdeDTO(UsuarioLoginDTO dto) {

        UserEntity usuario = new UserEntity();
          // Setea los datos del DTO
            usuario.setUsername(dto.getNombre());
            usuario.setPassword(dto.getPassword());

            // Busca los roles por ID
            Set<RoleEntity> roles = dto.getIdsRoles().stream()
                .map(id -> rolRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + id)))
                .collect(Collectors.toSet());

            usuario.setRoles(roles);

         
          

            // Llama al método que ya tienes para guardar
            return guardarUsuario(usuario);
    }
 */
   
      // Método para buscar un usuario por su username y rol
    // Método para buscar un usuario por su username y roleEnum
    public Optional<UserEntity> getUserByUsernameAndRole(String username) {
        // Buscar el RoleEntity correspondiente al RoleEnum
            return userRepositoryJPA.findByUsername(username);
    }

    //metodo para traer todos los roles
     public Iterable<RoleEntity> getAllRoles() {
                return roleRepository.findAll();
     }

     //metodo para traer todos los permisos
     public Iterable<PermissionsEntity> getAllPermissions() {
        return permisosRepository.findAll();
}

public ResponseEntity<UserEntity> crearUsuario(UserEntity user) {
    //encryptamos la contraseña
     
    user.setPassword(user.getPassword());

    // Si necesitas validar el username o realizar alguna otra operación, hazlo aquí
    user.setUsername(user.getUsername());

    // Asignar roles
    Set<RoleEntity> roles = user.getRoles().stream()
        .map(role -> roleRepository.findById(role.getId()).orElse(null))
        .filter(Objects::nonNull)
        .collect(Collectors.toSet());

    user.setRoles(roles);

    // Guardar el usuario con la contraseña encriptada
    UserEntity usuarioGuardado = repoentity.save(user);

  

    return ResponseEntity.ok(usuarioGuardado);
 }

 //metodo para buscar un usuario por el id
public UserEntity obtenerUsuarioId(Long id) {
    return repoentity.findById(id)
            .orElseThrow(() -> new NoSuchElementException("No existe el usuario con el id: " + id));
}

//metodo para actualizar un usuario
public UserEntity actualizarUsuario(Long id,UserEntity userDetails) {

    UserEntity user =  repoentity.findById(id)
            .orElseThrow(() -> new NoSuchElementException("No existe el usuario con el id: " + id));

              // Buscar rol por roleEnum
    Set<RoleEntity> nuevoRol = new HashSet<>();
    for (RoleEntity r : userDetails.getRoles()) {
        RoleEntity rol = roleRepository.findByRoleEnum(r.getRoleEnum())
            .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + r.getRoleEnum()));
        nuevoRol.add(rol);
    }
    
    user.setRoles(nuevoRol);
    user.setUsername(userDetails.getUsername());
    
    user.setPassword(userDetails.getPassword());
   
    UserEntity  userUpdate = repoentity.save(user);
    
    return userUpdate;
    } 

//metodo para eliminar un usuario
public void eliminarUsuario(Long id) {
UserEntity user = repoentity.findById(id)
        .orElseThrow(() -> new NoSuchElementException("No existe el usuario con el id: " + id));
    
     repoentity.delete(user);
 }


    public RoleEntity guardarRol(CreateRoleRequest request) {
        RoleEntity rol = new RoleEntity();
        rol.setRoleEnum(RoleEnum.valueOf(request.getRoleEnum()));

        Set<PermissionsEntity> permisos = request.getPermisoIds()
            .stream()
            .map(id -> permisosRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Permiso no encontrado: " + id)))
            .collect(Collectors.toSet());

        rol.setPermissionsList(permisos);
        return roleRepository.save(rol);
    }
 
}
