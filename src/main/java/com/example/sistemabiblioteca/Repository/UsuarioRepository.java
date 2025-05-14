package com.example.sistemabiblioteca.Repository;
import com.example.sistemabiblioteca.Model.UsuarioModel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioModel, Long> {}