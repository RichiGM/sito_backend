package com.sito.sito.controller;

import com.sito.sito.cqrs.commands.RegistrarProfesorCommand;
import com.sito.sito.cqrs.handlers.RegistrarProfesorCommandHandler;
import com.sito.sito.dao.EmpleadoDAO;
import com.sito.sito.model.Empleado;
import com.sito.sito.model.RegistroProfesorRequest;
import com.sito.sito.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rh")
@CrossOrigin(origins = "http://localhost:3000")
public class RecursosHumanosController {

    @Autowired
    private RegistrarProfesorCommandHandler registrarProfesorCommandHandler;

    @Autowired
    private EmpleadoDAO empleadoDAO;

    @PostMapping("/registrar-profesor")
    public ResponseEntity<?> registrarProfesor(@RequestBody RegistroProfesorRequest request) {
        try {
            RegistrarProfesorCommand command = new RegistrarProfesorCommand(
                    request.getMatricula(),
                    request.getUsuario(),
                    request.getContrasena(),
                    request.getNombreCompleto()
            );
            Usuario nuevoProfesor = registrarProfesorCommandHandler.handle(command);
            return ResponseEntity.ok(Map.of(
                    "message", "Profesor registrado exitosamente",
                    "matricula", nuevoProfesor.getMatricula(),
                    "usuario", nuevoProfesor.getUsuario()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/profesores")
    public ResponseEntity<List<Empleado>> getProfesores() {
        List<Empleado> profesores = empleadoDAO.findAll().stream()
                .filter(e -> "PROFESOR".equals(e.getPuesto()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(profesores);
    }

    @PutMapping("/profesores/{matricula}")
    public ResponseEntity<?> updateProfesor(@PathVariable String matricula, @RequestBody RegistroProfesorRequest request) {
        Empleado profesor = empleadoDAO.findByMatricula(matricula);
        if (profesor == null || !"PROFESOR".equals(profesor.getPuesto())) {
            return ResponseEntity.notFound().build();
        }
        profesor.setNombreCompleto(request.getNombreCompleto());
        // Si quieres actualizar más campos, agrégalos aquí
        empleadoDAO.save(profesor);
        return ResponseEntity.ok(Map.of("message", "Profesor actualizado exitosamente"));
    }

    @DeleteMapping("/profesores/{matricula}")
    public ResponseEntity<?> deleteProfesor(@PathVariable String matricula) {
        Empleado profesor = empleadoDAO.findByMatricula(matricula);
        if (profesor == null || !"PROFESOR".equals(profesor.getPuesto())) {
            return ResponseEntity.notFound().build();
        }
        empleadoDAO.delete(profesor);  // Asume que hay cascade delete para Usuario si es necesario
        return ResponseEntity.ok(Map.of("message", "Profesor eliminado exitosamente"));
    }
}