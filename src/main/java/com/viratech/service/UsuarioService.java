package com.viratech.service;

import com.viratech.domain.Usuario;
import com.viratech.service.repositories.UsuarioRepository;

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario salvarUsuario(Usuario usuario){
         return usuarioRepository.salvar(usuario);
     }
}
