package com.viratech.service;

import com.viratech.domain.Conta;
import com.viratech.domain.exceptions.ValidationException;
import com.viratech.service.enums.EventType;
import com.viratech.service.external.ContaEvent;
import com.viratech.service.repositories.ContaRepository;

import java.util.List;

public class ContaService {

    private final ContaRepository contaRepositoryRepository;
    private final ContaEvent contaEvent;

    public ContaService(ContaRepository contaRepositoryRepository, ContaEvent contaEvent){
        this.contaRepositoryRepository = contaRepositoryRepository;
        this.contaEvent = contaEvent;
    }

    public Conta salvarConta(Conta conta){

        List<Conta> contas = contaRepositoryRepository.obterContasPorUsuario(conta.getUsuario().getId());

        contas.stream().forEach(contaExistente -> {

            if(conta.getNome().equalsIgnoreCase(contaExistente.getNome())){
                throw new ValidationException(String.format("Já existe uma conta com esse nome", conta.getNome()));
            }
        });

        Conta contaSalva = contaRepositoryRepository.salvar(conta);
        contaEvent.dispatch(contaSalva, EventType.CREATED);

        return contaSalva;
    }

}
