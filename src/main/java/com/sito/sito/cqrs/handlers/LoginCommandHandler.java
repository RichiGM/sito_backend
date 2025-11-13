package com.sito.sito.cqrs.handlers;

import com.sito.sito.cqrs.commands.LoginCommand;
import com.sito.sito.model.LoginResponse;
import com.sito.sito.model.Usuario;
import com.sito.sito.repository.UsuarioRepository;
import com.sito.sito.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class LoginCommandHandler {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public LoginResponse handle(LoginCommand command) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        command.getUsuario(),
                        command.getContrasena())
        );

        String token = jwtUtil.generateToken(command.getUsuario());
        Usuario usuario = usuarioRepository.findByUsuario(command.getUsuario());

        return new LoginResponse(
                token,
                "Bienvenido al SITO",
                usuario.getRol().name(),
                usuario.getUsuario()
        );
    }
}