package com.sito.sito.controller;

import com.sito.sito.cqrs.commands.*;
import com.sito.sito.cqrs.handlers.*;
import com.sito.sito.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private LoginCommandHandler loginCommandHandler;

    @Autowired
    private CambiarContrasenaCommandHandler cambiarContrasenaCommandHandler;

    @Autowired
    private RegistrarAlumnoCommandHandler registrarAlumnoCommandHandler;

    @Autowired
    private RegistrarProfesorCommandHandler registrarProfesorCommandHandler;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            LoginCommand command = new LoginCommand(
                    loginRequest.getUsuario(),
                    loginRequest.getContrasena()
            );

            LoginResponse response = loginCommandHandler.handle(command);
            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body(
                    Map.of("error", "Usuario o contraseña incorrectos")
            );
        }
    }

    @PutMapping("/cambiar-contrasena")
    public ResponseEntity<?> cambiarContrasena(@RequestBody CambioContrasenaRequest request) {
        try {
            CambiarContrasenaCommand command = new CambiarContrasenaCommand(
                    request.getUsuario(),
                    request.getContrasenaActual(),
                    request.getNuevaContrasena()
            );

            cambiarContrasenaCommandHandler.handle(command);

            return ResponseEntity.ok(
                    Map.of("message", "Contraseña cambiada exitosamente")
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    Map.of("error", e.getMessage())
            );
        }
    }

    @PostMapping("/registrar-alumno")
    public ResponseEntity<?> registrarAlumno(@RequestBody RegistroAlumnoRequest request) {
        try {
            RegistrarAlumnoCommand command = new RegistrarAlumnoCommand(
                    request.getMatricula(),
                    request.getUsuario(),
                    request.getContrasena()
            );

            Usuario nuevoAlumno = registrarAlumnoCommandHandler.handle(command);

            return ResponseEntity.ok(Map.of(
                    "message", "Alumno registrado exitosamente",
                    "matricula", nuevoAlumno.getMatricula(),
                    "usuario", nuevoAlumno.getUsuario()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    Map.of("error", e.getMessage())
            );
        }
    }

    @PostMapping("/registrar-profesor")
    public ResponseEntity<?> registrarProfesor(@RequestBody RegistroProfesorRequest request) {
        try {
            RegistrarProfesorCommand command = new RegistrarProfesorCommand(
                    request.getMatricula(),
                    request.getUsuario(),
                    request.getContrasena()
            );

            Usuario nuevoProfesor = registrarProfesorCommandHandler.handle(command);

            return ResponseEntity.ok(Map.of(
                    "message", "Profesor registrado exitosamente",
                    "matricula", nuevoProfesor.getMatricula(),
                    "usuario", nuevoProfesor.getUsuario()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    Map.of("error", e.getMessage())
            );
        }
    }
}