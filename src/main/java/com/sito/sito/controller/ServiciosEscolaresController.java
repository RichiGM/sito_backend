package com.sito.sito.controller;

import com.sito.sito.cqrs.commands.ActualizarAlumnoCommand;
import com.sito.sito.cqrs.commands.InscribirAlumnoCommand;
import com.sito.sito.cqrs.commands.RegistrarGrupoCommand;
import com.sito.sito.cqrs.handlers.ActualizarAlumnoCommandHandler;
import com.sito.sito.cqrs.handlers.InscribirAlumnoCommandHandler;
import com.sito.sito.cqrs.handlers.RegistrarGrupoCommandHandler;
import com.sito.sito.model.*;
import com.sito.sito.repository.AlumnoRepository;
import com.sito.sito.repository.EmpleadoRepository;
import com.sito.sito.repository.GrupoRepository;
import com.sito.sito.repository.InscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/servicios")
@CrossOrigin(origins = "http://localhost:3000")
public class ServiciosEscolaresController {

    @Autowired
    private RegistrarGrupoCommandHandler registrarGrupoCommandHandler;

    @Autowired
    private InscribirAlumnoCommandHandler inscribirAlumnoCommandHandler;

    @Autowired
    private ActualizarAlumnoCommandHandler actualizarAlumnoCommandHandler;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @GetMapping("/alumnos")
    public ResponseEntity<List<Alumno>> getAlumnos(@RequestParam(required = false) String q) {
        List<Alumno> alumnos;
        if (q != null && !q.isEmpty()) {
            alumnos = alumnoRepository.findAll().stream()
                    .filter(a -> a.getMatricula().toLowerCase().contains(q.toLowerCase()) ||
                            a.getNombreCompleto().toLowerCase().contains(q.toLowerCase()))
                    .collect(Collectors.toList());
        } else {
            alumnos = alumnoRepository.findAll();
        }
        return ResponseEntity.ok(alumnos);
    }

    @GetMapping("/profesores")
    public ResponseEntity<List<Empleado>> getProfesores(@RequestParam(required = false) String q) {
        List<Empleado> profesores = empleadoRepository.findAll().stream()
                .filter(e -> "PROFESOR".equals(e.getPuesto()))
                .filter(e -> q == null || q.isEmpty() ||
                        e.getMatricula().toLowerCase().contains(q.toLowerCase()) ||
                        e.getNombreCompleto().toLowerCase().contains(q.toLowerCase()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(profesores);
    }

    @GetMapping("/grupos")
    public ResponseEntity<List<Grupo>> getGrupos(@RequestParam(required = false) String q) {
        List<Grupo> grupos;
        if (q != null && !q.isEmpty()) {
            grupos = grupoRepository.findAll().stream()
                    .filter(g -> g.getNombreGrupo().toLowerCase().contains(q.toLowerCase()) ||
                            g.getCarrera().toLowerCase().contains(q.toLowerCase()))
                    .collect(Collectors.toList());
        } else {
            grupos = grupoRepository.findAll();
        }
        return ResponseEntity.ok(grupos);
    }

    @GetMapping("/grupos/{id}")
    public ResponseEntity<?> getGrupoDetalle(@PathVariable Integer id) {
        Grupo grupo = grupoRepository.findById(id).orElse(null);
        if (grupo == null) {
            return ResponseEntity.notFound().build();
        }

        List<Inscripcion> inscripciones = inscripcionRepository.findByGrupoId(id);
        List<String> matriculas = inscripciones.stream()
                .map(Inscripcion::getAlumnoMatricula)
                .collect(Collectors.toList());

        List<Alumno> alumnos = alumnoRepository.findAllById(matriculas);

        return ResponseEntity.ok(Map.of(
                "grupo", grupo,
                "alumnos", alumnos
        ));
    }

    @PostMapping("/grupos")
    public ResponseEntity<?> registrarGrupo(@RequestBody RegistroGrupoRequest request) {
        try {
            RegistrarGrupoCommand command = new RegistrarGrupoCommand(
                    request.getNombreGrupo(), request.getCarrera(), request.getProfesorMatricula());
            Grupo nuevoGrupo = registrarGrupoCommandHandler.handle(command);
            return ResponseEntity.ok(Map.of("message", "Grupo registrado exitosamente", "grupoId", nuevoGrupo.getGrupoId()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/grupos/{id}")
    public ResponseEntity<?> updateGrupo(@PathVariable Integer id, @RequestBody RegistroGrupoRequest request) {
        Grupo grupo = grupoRepository.findById(id).orElse(null);
        if (grupo == null) {
            return ResponseEntity.notFound().build();
        }

        if (request.getNombreGrupo() != null) {
            grupo.setNombreGrupo(request.getNombreGrupo());
        }
        if (request.getCarrera() != null) {
            grupo.setCarrera(request.getCarrera());
        }
        if (request.getProfesorMatricula() != null) {
            grupo.setProfesorMatricula(request.getProfesorMatricula());
        }

        grupoRepository.save(grupo);
        return ResponseEntity.ok(Map.of("message", "Grupo actualizado exitosamente"));
    }

    @PostMapping("/grupos/{id}/profesor")
    public ResponseEntity<?> asignarProfesor(@PathVariable Integer id, @RequestBody Map<String, String> body) {
        Grupo grupo = grupoRepository.findById(id).orElse(null);
        if (grupo == null) {
            return ResponseEntity.notFound().build();
        }

        String profesorMatricula = body.get("profesorMatricula");
        grupo.setProfesorMatricula(profesorMatricula);
        grupoRepository.save(grupo);

        return ResponseEntity.ok(Map.of("message", "Profesor asignado exitosamente"));
    }

    @PostMapping("/inscripciones")
    public ResponseEntity<?> inscribirAlumno(@RequestBody InscribirAlumnoRequest request) {
        try {
            InscribirAlumnoCommand command = new InscribirAlumnoCommand(request.getGrupoId(), request.getAlumnoMatricula());
            inscribirAlumnoCommandHandler.handle(command);
            return ResponseEntity.ok(Map.of("message", "Alumno inscrito exitosamente"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/alumnos/{matricula}")
    public ResponseEntity<?> actualizarAlumno(@PathVariable String matricula, @RequestBody ActualizarAlumnoRequest request) {
        try {
            ActualizarAlumnoCommand command = new ActualizarAlumnoCommand(matricula, request.getNombreCompleto(), request.getCarrera());
            actualizarAlumnoCommandHandler.handle(command);
            return ResponseEntity.ok(Map.of("message", "Información del alumno actualizada exitosamente"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}