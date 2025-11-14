package com.sito.sito.cqrs.commands;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SubirCalificacionCommand {
    private Integer inscripcionId;
    private BigDecimal calificacion;
    private String profesorMatricula;

    public SubirCalificacionCommand(Integer inscripcionId, BigDecimal calificacion, String profesorMatricula) {
        this.inscripcionId = inscripcionId;
        this.calificacion = calificacion;
        this.profesorMatricula = profesorMatricula;
    }
}