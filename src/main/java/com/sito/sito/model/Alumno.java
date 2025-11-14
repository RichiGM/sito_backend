package com.sito.sito.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "alumnos")
public class Alumno {

    @Id
    @Column(length = 8)
    private String matricula;

    @Column(nullable = false, length = 255)
    private String nombreCompleto;

    @Column(nullable = false, length = 100)
    private String carrera;

    public Alumno() {}

    public Alumno(String matricula, String nombreCompleto, String carrera) {
        this.matricula = matricula;
        this.nombreCompleto = nombreCompleto;
        this.carrera = carrera;
    }
}