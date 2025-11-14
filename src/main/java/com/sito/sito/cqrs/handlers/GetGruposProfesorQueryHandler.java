package com.sito.sito.cqrs.handlers;

import com.sito.sito.cqrs.queries.GetGruposProfesorQuery;
import com.sito.sito.model.Grupo;
import com.sito.sito.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetGruposProfesorQueryHandler {

    @Autowired
    private GrupoRepository grupoRepository;

    public List<Grupo> handle(GetGruposProfesorQuery query) {
        return grupoRepository.findByProfesorMatricula(query.getProfesorMatricula());
    }
}