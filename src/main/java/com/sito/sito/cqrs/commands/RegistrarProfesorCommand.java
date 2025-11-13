package com.sito.sito.cqrs.commands;

import lombok.Data;

@Data
public class RegistrarProfesorCommand {
    private String matricula;
    private String usuario;
    private String contrasena;

    public RegistrarProfesorCommand(String matricula, String usuario, String contrasena) {
        this.matricula = matricula;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }
}