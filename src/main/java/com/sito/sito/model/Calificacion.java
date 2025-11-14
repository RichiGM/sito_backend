package com.sito.sito.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "calificaciones")
public class Calificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calificacion_id")
    private Integer calificacionId;

    @Column(name = "inscripcion_id", nullable = false, unique = true)
    private Integer inscripcionId;

    @Column(nullable = false)
    private BigDecimal calificacion;

    @Column(name = "fecha_registro", columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP")
    private Timestamp fechaRegistro;

    public Calificacion() {}

    public Calificacion(Integer inscripcionId, BigDecimal calificacion) {
        this.inscripcionId = inscripcionId;
        this.calificacion = calificacion;
    }
}