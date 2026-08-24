package com.viratech.infra;

import com.viratech.domain.Usuario;
import com.viratech.service.repositories.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioMemoryRepository implements UsuarioRepository {

    private List<Usuario> usuarios;
    private Long currentId;

    public UsuarioMemoryRepository(){
        currentId = 0L;
        usuarios = new ArrayList<>();
        salvar(new Usuario(null, "Usuario 1", "email@email.com", "123456"));
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        Usuario novoUsuario = new Usuario(nextId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha());
        usuarios.add(novoUsuario);
        return novoUsuario;
    }

    @Override
    public Optional<Usuario> getUserByEmail(String email) {
        return usuarios.stream().filter(usuario -> usuario.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    private Long nextId(){
        return ++currentId;
    }

    public void print(){
        System.out.println(usuarios);
    }
}
