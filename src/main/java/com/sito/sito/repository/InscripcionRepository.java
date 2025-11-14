package com.sito.sito.repository;

import com.sito.sito.model.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Integer> {
    List<Inscripcion> findByAlumnoMatricula(String alumnoMatricula);
    List<Inscripcion> findByGrupoId(Integer grupoId);

    @Query("SELECT i FROM Inscripcion i WHERE i.grupoId = :grupoId AND i.alumnoMatricula = :alumnoMatricula")
    Inscripcion findByGrupoIdAndAlumnoMatricula(@Param("grupoId") Integer grupoId, @Param("alumnoMatricula") String alumnoMatricula);
}