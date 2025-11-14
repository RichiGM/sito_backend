package com.sito.sito.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "empleados")
public class Empleado {

    @Id
    @Column(length = 8)
    private String matricula;

    @Column(nullable = false, length = 255)
    private String nombreCompleto;

    @Column(nullable = false, length = 50)
    private String puesto;

    public Empleado() {}

    public Empleado(String matricula, String nombreCompleto, String puesto) {
        this.matricula = matricula;
        this.nombreCompleto = nombreCompleto;
        this.puesto = puesto;
    }
}