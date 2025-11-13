// UsuarioService.java (MODIFICADO CORRECTAMENTE)
package com.sito.sito.service;

import com.sito.sito.cqrs.handlers.FindUsuarioByMatriculaQueryHandler;
import com.sito.sito.cqrs.handlers.FindUsuarioByUsuarioQueryHandler;
import com.sito.sito.cqrs.queries.FindUsuarioByMatriculaQuery;
import com.sito.sito.cqrs.queries.FindUsuarioByUsuarioQuery;
import com.sito.sito.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private FindUsuarioByUsuarioQueryHandler findUsuarioByUsuarioQueryHandler;

    @Autowired
    private FindUsuarioByMatriculaQueryHandler findUsuarioByMatriculaQueryHandler;

    public Usuario findByUsuario(String usuario) {
        FindUsuarioByUsuarioQuery query = new FindUsuarioByUsuarioQuery(usuario);
        return findUsuarioByUsuarioQueryHandler.handle(query);
    }

    public Usuario findByMatricula(String matricula) {
        FindUsuarioByMatriculaQuery query = new FindUsuarioByMatriculaQuery(matricula);
        return findUsuarioByMatriculaQueryHandler.handle(query);
    }
}