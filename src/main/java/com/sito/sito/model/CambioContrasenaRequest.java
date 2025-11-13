package com.sito.sito.model;

import lombok.Data;

@Data
public class CambioContrasenaRequest {
    private String usuario;
    private String contrasenaActual;
    private String nuevaContrasena;
}