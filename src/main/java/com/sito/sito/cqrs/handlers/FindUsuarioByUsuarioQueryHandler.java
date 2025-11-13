package com.sito.sito.cqrs.handlers;

import com.sito.sito.cqrs.queries.FindUsuarioByUsuarioQuery;
import com.sito.sito.dao.UsuarioDAO;
import com.sito.sito.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FindUsuarioByUsuarioQueryHandler {

    @Autowired
    private UsuarioDAO usuarioDAO;

    public Usuario handle(FindUsuarioByUsuarioQuery query) {
        return usuarioDAO.findByUsuario(query.getUsuario());
    }
}