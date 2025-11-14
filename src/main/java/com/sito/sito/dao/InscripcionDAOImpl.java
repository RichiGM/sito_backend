package com.sito.sito.dao;

import com.sito.sito.model.Inscripcion;
import com.sito.sito.repository.InscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InscripcionDAOImpl implements InscripcionDAO {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Override
    public Inscripcion findByGrupoIdAndAlumnoMatricula(Integer grupoId, String alumnoMatricula) {
        return inscripcionRepository.findByGrupoIdAndAlumnoMatricula(grupoId, alumnoMatricula);
    }

    @Override
    public Inscripcion save(Inscripcion inscripcion) {
        return inscripcionRepository.save(inscripcion);
    }
}