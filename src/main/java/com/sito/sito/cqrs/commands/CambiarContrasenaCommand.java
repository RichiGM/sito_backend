package com.sito.sito.cqrs.commands;

import lombok.Data;

@Data
public class CambiarContrasenaCommand {
    private String usuario;
    private String contrasenaActual;
    private String nuevaContrasena;

    public CambiarContrasenaCommand(String usuario, String contrasenaActual, String nuevaContrasena) {
        this.usuario = usuario;
        this.contrasenaActual = contrasenaActual;
        this.nuevaContrasena = nuevaContrasena;
    }
}