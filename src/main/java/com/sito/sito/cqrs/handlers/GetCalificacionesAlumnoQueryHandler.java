package com.sito.sito.cqrs.handlers;

import com.sito.sito.cqrs.queries.GetCalificacionesAlumnoQuery;
import com.sito.sito.model.Calificacion;
import com.sito.sito.model.Inscripcion;
import com.sito.sito.repository.CalificacionRepository;
import com.sito.sito.repository.InscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetCalificacionesAlumnoQueryHandler {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private CalificacionRepository calificacionRepository;

    public List<Calificacion> handle(GetCalificacionesAlumnoQuery query) {
        List<Integer> inscripcionIds = inscripcionRepository.findByAlumnoMatricula(query.getMatricula())
                .stream()
                .map(Inscripcion::getInscripcionId)
                .collect(Collectors.toList());

        return calificacionRepository.findByInscripcionIdIn(inscripcionIds);
    }
}