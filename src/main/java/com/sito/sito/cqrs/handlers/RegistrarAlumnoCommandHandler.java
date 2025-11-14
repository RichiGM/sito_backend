package com.sito.sito.cqrs.handlers;

import com.sito.sito.cqrs.commands.RegistrarAlumnoCommand;
import com.sito.sito.dao.AlumnoDAO;
import com.sito.sito.dao.UsuarioDAO;
import com.sito.sito.model.Alumno;
import com.sito.sito.model.RolEnum;
import com.sito.sito.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class RegistrarAlumnoCommandHandler {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Autowired
    private AlumnoDAO alumnoDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
                passwordEncoder.encode(command.getContrasena()),
                RolEnum.ALUMNO
        );
        usuarioDAO.save(nuevoAlumno);

        Alumno alumno = new Alumno(
                command.getMatricula(),
                command.getNombreCompleto(),
                command.getCarrera()
        );
        alumnoDAO.save(alumno);

        return nuevoAlumno;
    }
}