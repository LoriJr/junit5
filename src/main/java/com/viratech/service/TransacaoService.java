package com.viratech.service;

import com.viratech.domain.Transacao;
import com.viratech.domain.exceptions.ValidationException;
import com.viratech.service.repositories.TransacaoDao;

public class TransacaoService {

    private TransacaoDao transacaoDao;

    public Transacao salvar(Transacao transacao){

        if(transacao.getDescricao() == null) throw new ValidationException("Descrição inexistente");
        if(transacao.getValor() == null) throw new ValidationException("Valor inexistente");
        if(transacao.getData() == null) throw new ValidationException("Data inexistente");
        if(transacao.getConta() == null) throw new ValidationException("Conta inexistente");
        if(transacao.getStatus() == null) transacao.setStatus(false);

        return transacaoDao.salvar(transacao);
    }
}
