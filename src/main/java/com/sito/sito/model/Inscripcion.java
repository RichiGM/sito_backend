package com.sito.sito.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "inscripciones")
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inscripcion_id")
    private Integer inscripcionId;

    @Column(name = "grupo_id", nullable = false)
    private Integer grupoId;

    @Column(name = "alumno_matricula", nullable = false, length = 8)
    private String alumnoMatricula;

    public Inscripcion() {}

    public Inscripcion(Integer grupoId, String alumnoMatricula) {
        this.grupoId = grupoId;
        this.alumnoMatricula = alumnoMatricula;
    }
}