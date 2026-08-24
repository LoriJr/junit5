package com.viratech.service;

import com.viratech.domain.Usuario;
import com.viratech.domain.exceptions.ValidationException;
import com.viratech.service.repositories.UsuarioRepository;

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario salvarUsuario(Usuario usuario) {

        usuarioRepository.getUserByEmail(usuario.getEmail())
                .orElseThrow(() -> new ValidationException(String.format("Existe um cadastro com o email %s", usuario.getEmail())));

        return usuarioRepository.salvar(usuario);
    }
}
