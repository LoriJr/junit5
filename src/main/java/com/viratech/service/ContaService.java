package com.viratech.service;

import com.viratech.domain.Conta;
import com.viratech.domain.exceptions.ErrorSendNotification;
import com.viratech.domain.exceptions.ValidationException;
import com.viratech.service.enums.EventType;
import com.viratech.service.external.ContaEvent;
import com.viratech.service.repositories.ContaRepository;

import java.time.LocalDateTime;
import java.util.List;

public class ContaService {

    private final ContaRepository contaRepository;
    private final ContaEvent contaEvent;

    public ContaService(ContaRepository contaRepositoryRepository, ContaEvent contaEvent){
        this.contaRepository = contaRepositoryRepository;
        this.contaEvent = contaEvent;
    }

    public Conta salvarConta(Conta conta){

        List<Conta> contas = contaRepository.obterContasPorUsuario(conta.getUsuario().getId());

        boolean existeConta = contas.stream()
            .anyMatch(contaExistente ->
                conta.getNome().equalsIgnoreCase(contaExistente.getNome()));

                if(existeConta){
                    throw new ValidationException(String.format("Já existe uma conta com esse nome %s", conta.getNome()));
                }

        Conta contaSalva = contaRepository.salvar(
               new Conta(conta.getId(), conta.getNome(), conta.getUsuario()));

        try{
            contaEvent.dispatch(contaSalva, EventType.CREATED);
        }catch (RuntimeException e){
            contaRepository.delete(contaSalva);
              throw new ErrorSendNotification("Falha no envio de notificação");
        }
        return contaSalva;
    }

}
