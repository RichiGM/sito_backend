package com.sito.sito.controller;

import com.sito.sito.cqrs.queries.GetCalificacionesAlumnoQuery;
import com.sito.sito.cqrs.queries.GetGruposAlumnoQuery;
import com.sito.sito.cqrs.handlers.GetCalificacionesAlumnoQueryHandler;
import com.sito.sito.cqrs.handlers.GetGruposAlumnoQueryHandler;
import com.sito.sito.dao.AlumnoDAO;
import com.sito.sito.model.Alumno;
import com.sito.sito.model.Calificacion;
import com.sito.sito.model.Grupo;
import com.sito.sito.model.Usuario;
import com.sito.sito.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/alumno")
@CrossOrigin(origins = "http://localhost:3000")
public class AlumnoController {

    @Autowired
    private GetGruposAlumnoQueryHandler getGruposAlumnoQueryHandler;

    @Autowired
    private GetCalificacionesAlumnoQueryHandler getCalificacionesAlumnoQueryHandler;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AlumnoDAO alumnoDAO;

    @GetMapping("/info")
    public ResponseEntity<Alumno> getAlumnoInfo() {
        String matricula = getCurrentMatricula();
        Alumno alumno = alumnoDAO.findByMatricula(matricula);
        if (alumno == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(alumno);
    }

    @GetMapping("/grupos")
    public ResponseEntity<List<Grupo>> getGrupos() {
        String matricula = getCurrentMatricula();
        GetGruposAlumnoQuery query = new GetGruposAlumnoQuery(matricula);
        List<Grupo> grupos = getGruposAlumnoQueryHandler.handle(query);
        return ResponseEntity.ok(grupos);
    }

    @GetMapping("/calificaciones")
    public ResponseEntity<List<Calificacion>> getCalificaciones() {
        String matricula = getCurrentMatricula();
        GetCalificacionesAlumnoQuery query = new GetCalificacionesAlumnoQuery(matricula);
        List<Calificacion> calificaciones = getCalificacionesAlumnoQueryHandler.handle(query);
        return ResponseEntity.ok(calificaciones);
    }

    private String getCurrentMatricula() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Usuario user = usuarioService.findByUsuario(username);
        return user.getMatricula();
    }
}