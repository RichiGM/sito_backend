package com.sito.sito.cqrs.queries;

import lombok.Data;

@Data
public class GetGruposProfesorQuery {
    private String profesorMatricula;

    public GetGruposProfesorQuery(String profesorMatricula) {
        this.profesorMatricula = profesorMatricula;
    }
}