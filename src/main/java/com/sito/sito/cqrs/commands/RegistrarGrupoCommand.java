package com.sito.sito.cqrs.commands;

import lombok.Data;

@Data
public class RegistrarGrupoCommand {
    private String nombreGrupo;
    private String carrera;
    private String profesorMatricula;

    public RegistrarGrupoCommand(String nombreGrupo, String carrera, String profesorMatricula) {
        this.nombreGrupo = nombreGrupo;
        this.carrera = carrera;
        this.profesorMatricula = profesorMatricula;
    }
}