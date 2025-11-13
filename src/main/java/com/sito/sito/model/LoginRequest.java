package com.sito.sito.model;

import lombok.Data;

@Data
public class LoginRequest {
    private String usuario;
    private String contrasena;

    // Constructors
    public LoginRequest() {}

    public LoginRequest(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }
}