package com.example.sistemabiblioteca.Config;

import java.util.List;
import java.util.Set;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.example.sistemabiblioteca.Repository.PermissionRepository;
import com.example.sistemabiblioteca.Repository.RoleRepository;
import com.example.sistemabiblioteca.Repository.UsuarioEntityRepository;
import com.example.sistemabiblioteca.persistence.entity.PermissionsEntity;
import com.example.sistemabiblioteca.persistence.entity.RoleEntity;
import com.example.sistemabiblioteca.persistence.entity.RoleEnum;
import com.example.sistemabiblioteca.persistence.entity.UserEntity;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http -> {
                    // configurar los endpoints privados
                    http.requestMatchers("/login").permitAll();
                    http.requestMatchers("/user/registrar").permitAll();
                    http.requestMatchers("/user/rol").permitAll();
                    http.requestMatchers("/user/roles").permitAll();
                    http.requestMatchers("/user/permisos").permitAll();
                    http.requestMatchers("/user/registrar").permitAll();
                    http.requestMatchers(HttpMethod.GET, "/user/{id}").permitAll();
                    http.requestMatchers(HttpMethod.GET, "/user/roles").permitAll();
                    http.requestMatchers(HttpMethod.GET, "/user/permisos").permitAll();
                    http.requestMatchers(HttpMethod.PUT, "/user/{id}").permitAll();
                    http.requestMatchers(HttpMethod.DELETE, "/user/{id}").permitAll();
                    http.requestMatchers("/home").permitAll();
                    http.requestMatchers(HttpMethod.GET, "/user/all").permitAll();

                    http.anyRequest().permitAll();
                })

                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    CommandLineRunner init(PermissionRepository permissionRepository) {
        return args -> {
            List<String> permissions = List.of("CREATE", "READ", "UPDATE", "DELETE", "REFACTOR");

            for (String name : permissions) {
                if (!permissionRepository.existsByName(name)) {
                    PermissionsEntity permission = PermissionsEntity.builder()
                            .name(name)
                            .build();
                    permissionRepository.save(permission);
                }
            }
        };
    }

    @Bean
    CommandLineRunner initRoles(
            RoleRepository roleRepository,
            PermissionRepository permissionRepository) {
        return args -> {
            PermissionsEntity create = permissionRepository.findByName("CREATE").orElseThrow();
            PermissionsEntity read = permissionRepository.findByName("READ").orElseThrow();
            PermissionsEntity update = permissionRepository.findByName("UPDATE").orElseThrow();
            PermissionsEntity delete = permissionRepository.findByName("DELETE").orElseThrow();

            if (!roleRepository.existsByRoleEnum(RoleEnum.ESTUDIANTE)) {
                RoleEntity roleUser = RoleEntity.builder()
                        .roleEnum(RoleEnum.ESTUDIANTE)
                        .permissionsList(Set.of(create, read))
                        .build();
                roleRepository.save(roleUser);
            }
            if (!roleRepository.existsByRoleEnum(RoleEnum.BIBLIOTECARIO)) {
                RoleEntity roleBibliotecario = RoleEntity.builder()
                        .roleEnum(RoleEnum.BIBLIOTECARIO)
                        .permissionsList(Set.of(create, read, update, delete))
                        .build();
                roleRepository.save(roleBibliotecario);
            }
            if (!roleRepository.existsByRoleEnum(RoleEnum.BIBLIOTECARIO)) {
                RoleEntity roleBibliotecario = RoleEntity.builder()
                        .roleEnum(RoleEnum.BIBLIOTECARIO)
                        .permissionsList(Set.of(create, read, update, delete))
                        .build();
                roleRepository.save(roleBibliotecario);
            }
            if (!roleRepository.existsByRoleEnum(RoleEnum.ADMIN)) {
                RoleEntity roleAdmin = RoleEntity.builder()
                        .roleEnum(RoleEnum.ADMIN)
                        .permissionsList(Set.of(create, read, update, delete))
                        .build();
                roleRepository.save(roleAdmin);
            }
        };
    }

    @Bean
    CommandLineRunner initUsers(
            UsuarioEntityRepository usuarioRepository, RoleRepository roleRepository

    ) {
        return args -> {
            RoleEntity estudianteRole = roleRepository
                    .findByRoleEnum(RoleEnum.ADMIN)
                    .orElseThrow(() -> new RuntimeException("Rol Administrador no encontrado"));

            if (!usuarioRepository.existsByUsername("administrador")) {
                UserEntity usuario = UserEntity.builder()
                        .username("administrador")
                        .password("123456")
                        .isEnabled(true)
                        .accountNoExpired(true)
                        .accountNolocked(true)
                        .credentialNoExpired(true)
                        .roles(Set.of(estudianteRole))
                        .build();

                usuarioRepository.save(usuario);
            }
        };

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // contraseña sin cifrar
    }

}
