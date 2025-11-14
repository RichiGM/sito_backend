package com.sito.sito.dao;

import com.sito.sito.model.Empleado;
import com.sito.sito.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List; // <-- Importante añadir este import

@Repository
public class EmpleadoDAOImpl implements EmpleadoDAO {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public Empleado findByMatricula(String matricula) {
        return empleadoRepository.findById(matricula).orElse(null);
    }

    @Override
    public Empleado save(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    @Override
    public List<Empleado> findAll() {
        return empleadoRepository.findAll();
    }

    @Override
    public void delete(Empleado empleado) {
        empleadoRepository.delete(empleado);
    }
}