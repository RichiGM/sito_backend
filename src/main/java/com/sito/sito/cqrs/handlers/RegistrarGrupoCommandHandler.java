package com.sito.sito.cqrs.handlers;

import com.sito.sito.cqrs.commands.RegistrarGrupoCommand;
import com.sito.sito.dao.GrupoDAO;
import com.sito.sito.dao.UsuarioDAO;
import com.sito.sito.model.Grupo;
import com.sito.sito.model.RolEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistrarGrupoCommandHandler {

    @Autowired
    private GrupoDAO grupoDAO;

    @Autowired
    private UsuarioDAO usuarioDAO;

    public Grupo handle(RegistrarGrupoCommand command) {
        if (usuarioDAO.findByMatricula(command.getProfesorMatricula()) == null || !usuarioDAO.findByMatricula(command.getProfesorMatricula()).getRol().equals(RolEnum.PROFESOR)) {
            throw new RuntimeException("Profesor no encontrado o no válido");
        }

        Grupo nuevoGrupo = new Grupo(
                command.getNombreGrupo(),
                command.getCarrera(),
                command.getProfesorMatricula()
        );
        return grupoDAO.save(nuevoGrupo);
    }
}