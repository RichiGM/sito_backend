package com.sito.sito.dao;

import com.sito.sito.model.Grupo;
import com.sito.sito.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GrupoDAOImpl implements GrupoDAO {

    @Autowired
    private GrupoRepository grupoRepository;

    @Override
    public Grupo findById(Integer id) {
        return grupoRepository.findById(id).orElse(null);
    }

    @Override
    public Grupo save(Grupo grupo) {
        return grupoRepository.save(grupo);
    }
}