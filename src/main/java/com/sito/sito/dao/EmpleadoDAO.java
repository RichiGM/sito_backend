package com.sito.sito.dao;

import com.sito.sito.model.Empleado;
import java.util.List;

public interface EmpleadoDAO {
    Empleado findByMatricula(String matricula);
    Empleado save(Empleado empleado);
    List<Empleado> findAll();
    void delete(Empleado empleado);
}