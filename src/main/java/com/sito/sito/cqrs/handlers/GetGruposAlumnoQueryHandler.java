package com.sito.sito.cqrs.handlers;

import com.sito.sito.cqrs.queries.GetGruposAlumnoQuery;
import com.sito.sito.model.Grupo;
import com.sito.sito.model.Inscripcion;
import com.sito.sito.repository.GrupoRepository;
import com.sito.sito.repository.InscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetGruposAlumnoQueryHandler {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private GrupoRepository grupoRepository;

    public List<Grupo> handle(GetGruposAlumnoQuery query) {
        List<Integer> grupoIds = inscripcionRepository.findByAlumnoMatricula(query.getMatricula())
                .stream()
                .map(Inscripcion::getGrupoId)
                .collect(Collectors.toList());

        return grupoRepository.findAllById(grupoIds);
    }
}