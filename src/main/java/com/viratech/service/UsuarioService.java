package com.viratech.service;

import com.viratech.domain.Usuario;
import com.viratech.domain.exceptions.ValidationException;
import com.viratech.service.repositories.UsuarioRepository;

import java.util.Optional;

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario salvarUsuario(Usuario usuario) {

        usuarioRepository.getUserByEmail(usuario.getEmail()).ifPresent(user -> {
            throw new ValidationException(String.format("Existe um cadastro com o email %s", usuario.getEmail()));
        });

        return usuarioRepository.salvar(usuario);
    }

    public Optional<Usuario> getUserByEmail(String email){
        return usuarioRepository.getUserByEmail(email);
    }
}
