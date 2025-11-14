package com.sito.sito.model;

import lombok.Data;

@Data
public class AlumnoConInscripcion {
    private Integer inscripcionId;
    private String matricula;
    private String nombreCompleto;
    private String carrera;
}