package com.sito.sito.repository;

import com.sito.sito.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Usuario findByUsuario(String usuario);
    Usuario findByMatricula(String matricula);
}