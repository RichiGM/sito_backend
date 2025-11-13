package com.sito.sito.dao;

import com.sito.sito.model.Usuario;
import com.sito.sito.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDAOImpl implements UsuarioDAO {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario findByUsuario(String usuario) {
        return usuarioRepository.findByUsuario(usuario);
    }

    @Override
    public Usuario findByMatricula(String matricula) {
        return usuarioRepository.findByMatricula(matricula);
    }

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}