package com.sito.sito.cqrs.handlers;

import com.sito.sito.cqrs.queries.GetAlumnosGrupoQuery;
import com.sito.sito.model.Alumno;
import com.sito.sito.model.AlumnoConInscripcion;
import com.sito.sito.model.Inscripcion;
import com.sito.sito.repository.AlumnoRepository;
import com.sito.sito.repository.InscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class GetAlumnosGrupoQueryHandler {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    public List<AlumnoConInscripcion> handle(GetAlumnosGrupoQuery query) {
        List<Inscripcion> inscripciones = inscripcionRepository.findByGrupoId(query.getGrupoId());
        List<String> matriculas = inscripciones.stream()
                .map(Inscripcion::getAlumnoMatricula)
                .collect(Collectors.toList());

        List<Alumno> alumnos = alumnoRepository.findAllById(matriculas);

        // Map de matricula a alumno para join rápido
        Map<String, Alumno> alumnoMap = alumnos.stream()
                .collect(Collectors.toMap(Alumno::getMatricula, a -> a));

        // Crea lista de DTOs con inscripcionId
        return inscripciones.stream()
                .map(insc -> {
                    Alumno alumno = alumnoMap.get(insc.getAlumnoMatricula());
                    if (alumno != null) {
                        AlumnoConInscripcion dto = new AlumnoConInscripcion();
                        dto.setInscripcionId(insc.getInscripcionId());
                        dto.setMatricula(alumno.getMatricula());
                        dto.setNombreCompleto(alumno.getNombreCompleto());
                        dto.setCarrera(alumno.getCarrera());
                        return dto;
                    }
                    return null;
                })
                .filter(dto -> dto != null)
                .collect(Collectors.toList());
    }
}