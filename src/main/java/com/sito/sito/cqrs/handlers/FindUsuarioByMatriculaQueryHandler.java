package com.sito.sito.cqrs.handlers;

import com.sito.sito.cqrs.queries.FindUsuarioByMatriculaQuery;
import com.sito.sito.dao.UsuarioDAO;
import com.sito.sito.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FindUsuarioByMatriculaQueryHandler {

    @Autowired
    private UsuarioDAO usuarioDAO;

    public Usuario handle(FindUsuarioByMatriculaQuery query) {
        return usuarioDAO.findByMatricula(query.getMatricula());
    }
}