package com.sito.sito.cqrs.commands;

import lombok.Data;

@Data
public class LoginCommand {
    private String usuario;
    private String contrasena;

    public LoginCommand(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }
}