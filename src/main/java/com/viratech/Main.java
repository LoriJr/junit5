package com.viratech;

import com.viratech.domain.Usuario;
import com.viratech.infra.UsuarioMemoryRepository;

public class Main {
    public static void main(String[] args) {

        UsuarioMemoryRepository memory = new UsuarioMemoryRepository();

        memory.salvar(new Usuario(null, "Usuário 2", "email2@email", "senha123"));
        memory.print();
    }
}