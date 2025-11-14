// src/main/java/com/sito/sito/controller/ProfesorController.java
package com.sito.sito.controller;

import com.sito.sito.cqrs.commands.SubirCalificacionCommand;
import com.sito.sito.cqrs.handlers.GetAlumnosGrupoQueryHandler;
import com.sito.sito.cqrs.handlers.GetGruposProfesorQueryHandler;
import com.sito.sito.cqrs.handlers.SubirCalificacionCommandHandler;
import com.sito.sito.cqrs.queries.GetAlumnosGrupoQuery;
import com.sito.sito.cqrs.queries.GetGruposProfesorQuery;
import com.sito.sito.dao.EmpleadoDAO;
import com.sito.sito.model.*;
import com.sito.sito.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/profesor")
@CrossOrigin(origins = "http://localhost:3000")
public class ProfesorController {

    @Autowired
    private GetGruposProfesorQueryHandler getGruposProfesorQueryHandler;

    @Autowired
    private GetAlumnosGrupoQueryHandler getAlumnosGrupoQueryHandler;

    @Autowired
    private SubirCalificacionCommandHandler subirCalificacionCommandHandler;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EmpleadoDAO empleadoDAO;

    @GetMapping("/info")
    public ResponseEntity<Empleado> getProfesorInfo() {
        String matricula = getCurrentMatricula();
        Empleado profesor = empleadoDAO.findByMatricula(matricula);
        if (profesor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(profesor);
    }

    @GetMapping("/grupos")
    public ResponseEntity<List<Grupo>> getGrupos() {
        String matricula = getCurrentMatricula();
        GetGruposProfesorQuery query = new GetGruposProfesorQuery(matricula);
        List<Grupo> grupos = getGruposProfesorQueryHandler.handle(query);
        return ResponseEntity.ok(grupos);
    }

    @GetMapping("/grupos/{grupoId}/alumnos")
    public ResponseEntity<List<AlumnoConInscripcion>> getAlumnos(@PathVariable Integer grupoId) {
        GetAlumnosGrupoQuery query = new GetAlumnosGrupoQuery(grupoId);
        List<AlumnoConInscripcion> alumnos = getAlumnosGrupoQueryHandler.handle(query);
        return ResponseEntity.ok(alumnos);
    }

    @PostMapping("/calificaciones")
    public ResponseEntity<?> subirCalificacion(@RequestBody SubirCalificacionRequest request) {
        try {
            String profesorMatricula = getCurrentMatricula();
            SubirCalificacionCommand command = new SubirCalificacionCommand(
                    request.getInscripcionId(), request.getCalificacion(), profesorMatricula);
            subirCalificacionCommandHandler.handle(command);
            return ResponseEntity.ok(Map.of("message", "Calificación subida exitosamente"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    private String getCurrentMatricula() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Usuario user = usuarioService.findByUsuario(username);
        return user.getMatricula();
    }
}