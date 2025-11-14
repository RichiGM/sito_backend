package com.sito.sito.cqrs.queries;

import lombok.Data;

@Data
public class GetCalificacionesAlumnoQuery {
    private String matricula;

    public GetCalificacionesAlumnoQuery(String matricula) {
        this.matricula = matricula;
    }
}