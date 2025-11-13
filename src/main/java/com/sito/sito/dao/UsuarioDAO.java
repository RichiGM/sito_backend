package com.sito.sito.dao;

import com.sito.sito.model.Usuario;

public interface UsuarioDAO {
    Usuario findByUsuario(String usuario);
    Usuario findByMatricula(String matricula);
    Usuario save(Usuario usuario);
}