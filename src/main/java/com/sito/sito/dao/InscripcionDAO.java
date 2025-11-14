package com.sito.sito.dao;

import com.sito.sito.model.Inscripcion;

public interface InscripcionDAO {
    Inscripcion findByGrupoIdAndAlumnoMatricula(Integer grupoId, String alumnoMatricula);
    Inscripcion save(Inscripcion inscripcion);
}