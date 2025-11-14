package com.sito.sito.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "grupos")
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grupo_id")
    private Integer grupoId;

    @Column(name = "nombre_grupo", nullable = false, length = 150)
    private String nombreGrupo;

    @Column(nullable = false, length = 100)
    private String carrera;

    @Column(name = "profesor_matricula", nullable = false, length = 8)
    private String profesorMatricula;

    public Grupo() {}

    public Grupo(String nombreGrupo, String carrera, String profesorMatricula) {
        this.nombreGrupo = nombreGrupo;
        this.carrera = carrera;
        this.profesorMatricula = profesorMatricula;
    }
}