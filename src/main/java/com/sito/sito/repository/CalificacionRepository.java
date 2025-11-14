package com.sito.sito.repository;

import com.sito.sito.model.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CalificacionRepository extends JpaRepository<Calificacion, Integer> {
    Optional<Calificacion> findByInscripcionId(Integer inscripcionId);
    List<Calificacion> findByInscripcionIdIn(List<Integer> inscripcionIds);
}