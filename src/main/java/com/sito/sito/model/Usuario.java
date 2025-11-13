package com.sito.sito.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @Column(length = 8)
    private String matricula;

    @Column(unique = true, nullable = false, length = 50)
    private String usuario;

    @Column(nullable = false)
    private String contrasena;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RolEnum rol;

    // Constructors
    public Usuario() {}

    public Usuario(String matricula, String usuario, String contrasena, RolEnum rol) {
        this.matricula = matricula;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.rol = rol;
    }

}