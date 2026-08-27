package com.viratech.service;

import com.viratech.domain.Conta;
import com.viratech.domain.Usuario;
import com.viratech.domain.exceptions.ValidationException;
import com.viratech.service.repositories.ContaRepository;
import com.viratech.service.repositories.UsuarioRepository;

public class ContaService {

    private final ContaRepository contaRepositoryRepository;
    private final UsuarioRepository usuarioRepository;

    public ContaService(ContaRepository contaRepositoryRepository, UsuarioRepository usuarioRepository){
        this.contaRepositoryRepository = contaRepositoryRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Conta salvarConta(Conta conta){
        return contaRepositoryRepository.salvar(conta);
    }

}
