package com.viratech.service;

import com.viratech.domain.Transacao;
import com.viratech.domain.exceptions.ValidationException;
import com.viratech.service.repositories.TransacaoDao;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TransacaoService {

    private TransacaoDao transacaoDao;

    public Transacao salvar(Transacao transacao){

       if (LocalDateTime.now().getHour() > 10){
           throw new RuntimeException("Tente novamente amanhã");
       }

        if(transacao.getDescricao() == null) throw new ValidationException("Descrição inexistente");
        if(transacao.getValor() == null) throw new ValidationException("Valor inexistente");
        if(transacao.getData() == null) throw new ValidationException("Data inexistente");
        if(transacao.getConta() == null) throw new ValidationException("Conta inexistente");
        if(transacao.getStatus() == null) transacao.setStatus(false);

        return transacaoDao.salvar(transacao);
    }
}
