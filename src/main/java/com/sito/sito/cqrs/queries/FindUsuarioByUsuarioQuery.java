package com.sito.sito.cqrs.queries;

import lombok.Data;

@Data
public class FindUsuarioByUsuarioQuery {
    private String usuario;

    public FindUsuarioByUsuarioQuery(String usuario) {
        this.usuario = usuario;
    }
}