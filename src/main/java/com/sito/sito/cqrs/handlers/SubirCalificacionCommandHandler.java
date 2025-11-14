package com.sito.sito.cqrs.handlers;

import com.sito.sito.cqrs.commands.SubirCalificacionCommand;
import com.sito.sito.model.Calificacion;
import com.sito.sito.model.Grupo;
import com.sito.sito.model.Inscripcion;
import com.sito.sito.repository.CalificacionRepository;
import com.sito.sito.repository.GrupoRepository;
import com.sito.sito.repository.InscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SubirCalificacionCommandHandler {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private CalificacionRepository calificacionRepository;

    public void handle(SubirCalificacionCommand command) {
        Optional<Inscripcion> optInscripcion = inscripcionRepository.findById(command.getInscripcionId());
        if (optInscripcion.isEmpty()) {
            throw new RuntimeException("Inscripción no encontrada");
        }
        Inscripcion inscripcion = optInscripcion.get();

        Optional<Grupo> optGrupo = grupoRepository.findById(inscripcion.getGrupoId());
        if (optGrupo.isEmpty() || !optGrupo.get().getProfesorMatricula().equals(command.getProfesorMatricula())) {
            throw new RuntimeException("No autorizado para subir calificación en este grupo");
        }

        Optional<Calificacion> optCalif = calificacionRepository.findByInscripcionId(command.getInscripcionId());
        Calificacion calificacion;
        if (optCalif.isPresent()) {
            calificacion = optCalif.get();
            calificacion.setCalificacion(command.getCalificacion());
        } else {
            calificacion = new Calificacion(command.getInscripcionId(), command.getCalificacion());
        }
        calificacionRepository.save(calificacion);
    }
}