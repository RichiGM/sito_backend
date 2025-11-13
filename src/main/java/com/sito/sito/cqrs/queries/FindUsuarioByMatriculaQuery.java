package com.sito.sito.cqrs.queries;

import lombok.Data;

@Data
public class FindUsuarioByMatriculaQuery {
    private String matricula;

    public FindUsuarioByMatriculaQuery(String matricula) {
        this.matricula = matricula;
    }
}