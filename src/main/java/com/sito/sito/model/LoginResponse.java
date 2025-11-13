package com.sito.sito.model;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private String mensaje;
    private String rol;
    private String usuario;

    // Constructors
    public LoginResponse() {}

    public LoginResponse(String token, String mensaje, String rol, String usuario) {
        this.token = token;
        this.mensaje = mensaje;
        this.rol = rol;
        this.usuario = usuario;
    }
}