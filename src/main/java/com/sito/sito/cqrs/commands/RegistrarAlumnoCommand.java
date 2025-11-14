package com.sito.sito.cqrs.commands;

import lombok.Data;

@Data
public class RegistrarAlumnoCommand {
    private String matricula;
    private String usuario;
    private String contrasena;
    private String nombreCompleto;
    private String carrera;

    public RegistrarAlumnoCommand(String matricula, String usuario, String contrasena, String nombreCompleto, String carrera) {
        this.matricula = matricula;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.nombreCompleto = nombreCompleto;
        this.carrera = carrera;
    }
}