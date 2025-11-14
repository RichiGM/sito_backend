package com.sito.sito.cqrs.commands;

import lombok.Data;

@Data
public class InscribirAlumnoCommand {
    private Integer grupoId;
    private String alumnoMatricula;

    public InscribirAlumnoCommand(Integer grupoId, String alumnoMatricula) {
        this.grupoId = grupoId;
        this.alumnoMatricula = alumnoMatricula;
    }
}