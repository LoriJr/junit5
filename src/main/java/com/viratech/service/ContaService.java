package com.viratech.service;

import com.viratech.domain.Conta;
import com.viratech.domain.exceptions.ValidationException;
import com.viratech.service.repositories.ContaRepository;
import com.viratech.service.repositories.UsuarioRepository;

import java.util.List;

public class ContaService {

    private final ContaRepository contaRepositoryRepository;
    private final UsuarioRepository usuarioRepository;

    public ContaService(ContaRepository contaRepositoryRepository, UsuarioRepository usuarioRepository){
        this.contaRepositoryRepository = contaRepositoryRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Conta salvarConta(Conta conta){

        List<Conta> contas = contaRepositoryRepository.obterContasPorUsuario(conta.getUsuario().getId());

        contas.stream().forEach(contaExistente -> {

            if(conta.getNome().equalsIgnoreCase(contaExistente.getNome())){
                throw new ValidationException(String.format("Já existe uma conta com esse nome", conta.getNome()));
            }
        });

        return contaRepositoryRepository.salvar(conta);
    }

}
