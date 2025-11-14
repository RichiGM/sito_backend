package com.sito.sito.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SubirCalificacionRequest {
    private Integer inscripcionId;
    private BigDecimal calificacion;
}