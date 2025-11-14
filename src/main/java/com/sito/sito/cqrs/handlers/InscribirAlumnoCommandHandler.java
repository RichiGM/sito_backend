package com.sito.sito.cqrs.handlers;

import com.sito.sito.cqrs.commands.InscribirAlumnoCommand;
import com.sito.sito.dao.AlumnoDAO;
import com.sito.sito.dao.GrupoDAO;
import com.sito.sito.dao.InscripcionDAO;
import com.sito.sito.model.Inscripcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InscribirAlumnoCommandHandler {

    @Autowired
    private InscripcionDAO inscripcionDAO;

    @Autowired
    private GrupoDAO grupoDAO;

    @Autowired
    private AlumnoDAO alumnoDAO;

    public void handle(InscribirAlumnoCommand command) {
        if (grupoDAO.findById(command.getGrupoId()) == null) {
            throw new RuntimeException("Grupo no encontrado");
        }
        if (alumnoDAO.findByMatricula(command.getAlumnoMatricula()) == null) {
            throw new RuntimeException("Alumno no encontrado");
        }
        if (inscripcionDAO.findByGrupoIdAndAlumnoMatricula(command.getGrupoId(), command.getAlumnoMatricula()) != null) {
            throw new RuntimeException("Alumno ya inscrito en el grupo");
        }

        Inscripcion nuevaInscripcion = new Inscripcion(
                command.getGrupoId(),
                command.getAlumnoMatricula()
        );
        inscripcionDAO.save(nuevaInscripcion);
    }
}