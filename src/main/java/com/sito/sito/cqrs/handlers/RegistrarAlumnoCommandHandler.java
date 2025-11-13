package com.sito.sito.cqrs.handlers;

import com.sito.sito.cqrs.commands.RegistrarAlumnoCommand;
import com.sito.sito.dao.UsuarioDAO;
import com.sito.sito.model.RolEnum;
import com.sito.sito.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistrarAlumnoCommandHandler {

    @Autowired
    private UsuarioDAO usuarioDAO;

    public Usuario handle(RegistrarAlumnoCommand command) {
        if (usuarioDAO.findByUsuario(command.getUsuario()) != null) {
            throw new RuntimeException("El usuario ya existe");
        }
        if (usuarioDAO.findByMatricula(command.getMatricula()) != null) {
            throw new RuntimeException("La matrícula ya existe");
        }

        Usuario nuevoAlumno = new Usuario(
                command.getMatricula(),
                command.getUsuario(),
                command.getContrasena(),
                RolEnum.ALUMNO
        );
        return usuarioDAO.save(nuevoAlumno);
    }
}