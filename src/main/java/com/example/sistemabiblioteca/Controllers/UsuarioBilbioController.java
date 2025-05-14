package com.example.sistemabiblioteca.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.sistemabiblioteca.Model.RegistroCompletoDTO;
import com.example.sistemabiblioteca.Service.UsuarioRegistroBibliotecaService;

@RequestMapping("/user_bilbio")
@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioBilbioController {


    private final UsuarioRegistroBibliotecaService usuarioBilbioService;

    public UsuarioBilbioController(UsuarioRegistroBibliotecaService usuarioBilbioService){
        this.usuarioBilbioService = usuarioBilbioService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario(@RequestBody RegistroCompletoDTO dto) {
        usuarioBilbioService.RegisterUserBiblio(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado correctamente");
    }

}
