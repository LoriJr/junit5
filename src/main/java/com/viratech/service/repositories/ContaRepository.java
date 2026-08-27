package com.viratech.service.repositories;

import com.viratech.domain.Conta;

import java.util.List;

public interface ContaRepository {

    Conta salvar(Conta conta);

    List<Conta> obterContasPorUsuario(Long usuarioId);
}
