package com.sito.sito.cqrs.queries;

import lombok.Data;

@Data
public class GetGruposAlumnoQuery {
    private String matricula;

    public GetGruposAlumnoQuery(String matricula) {
        this.matricula = matricula;
    }
}