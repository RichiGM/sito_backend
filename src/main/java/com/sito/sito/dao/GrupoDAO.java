package com.sito.sito.dao;

import com.sito.sito.model.Grupo;

public interface GrupoDAO {
    Grupo findById(Integer id);
    Grupo save(Grupo grupo);
}