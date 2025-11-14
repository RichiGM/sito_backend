package com.sito.sito.cqrs.commands;

import lombok.Data;

@Data
public class ActualizarAlumnoCommand {
    private String matricula;
    private String nombreCompleto;
    private String carrera;

    public ActualizarAlumnoCommand(String matricula, String nombreCompleto, String carrera) {
        this.matricula = matricula;
        this.nombreCompleto = nombreCompleto;
        this.carrera = carrera;
    }
}