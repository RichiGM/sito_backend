package com.sito.sito.dao;

import com.sito.sito.model.Alumno;

public interface AlumnoDAO {
    Alumno findByMatricula(String matricula);
    Alumno save(Alumno alumno);
}