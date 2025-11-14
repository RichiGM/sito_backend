package com.sito.sito.cqrs.handlers;

import com.sito.sito.cqrs.commands.ActualizarAlumnoCommand;
import com.sito.sito.dao.AlumnoDAO;
import com.sito.sito.model.Alumno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActualizarAlumnoCommandHandler {

    @Autowired
    private AlumnoDAO alumnoDAO;

    public void handle(ActualizarAlumnoCommand command) {
        Alumno alumno = alumnoDAO.findByMatricula(command.getMatricula());
        if (alumno == null) {
            throw new RuntimeException("Alumno no encontrado");
        }

        alumno.setNombreCompleto(command.getNombreCompleto());
        alumno.setCarrera(command.getCarrera());
        alumnoDAO.save(alumno);
    }
}