package com.sito.sito.cqrs.handlers;

import com.sito.sito.cqrs.commands.CambiarContrasenaCommand;
import com.sito.sito.dao.UsuarioDAO;
import com.sito.sito.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CambiarContrasenaCommandHandler {

    @Autowired
    private UsuarioDAO usuarioDAO;

    public void handle(CambiarContrasenaCommand command) {
        Usuario user = usuarioDAO.findByUsuario(command.getUsuario());
        if (user == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        if (!command.getContrasenaActual().equals(user.getContrasena())) {
            throw new RuntimeException("Contraseña actual incorrecta");
        }

        user.setContrasena(command.getNuevaContrasena());
        usuarioDAO.save(user);
    }
}