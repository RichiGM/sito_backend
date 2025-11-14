package com.sito.sito.dao;

import com.sito.sito.model.Alumno;
import com.sito.sito.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AlumnoDAOImpl implements AlumnoDAO {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Override
    public Alumno findByMatricula(String matricula) {
        return alumnoRepository.findById(matricula).orElse(null);
    }

    @Override
    public Alumno save(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }
}