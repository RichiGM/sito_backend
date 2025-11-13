package com.sito.sito.model;

import lombok.Data;

@Data
public class RegistroAlumnoRequest {
    private String matricula;
    private String usuario;
    private String contrasena;
}