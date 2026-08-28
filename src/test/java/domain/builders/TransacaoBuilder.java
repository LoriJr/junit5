package domain.builders;

import com.viratech.domain.Conta;
import com.viratech.domain.Transacao;

import java.time.LocalDate;


public class TransacaoBuilder {

    private Transacao elemento;
    private TransacaoBuilder(){}

    public static TransacaoBuilder umTransacao() {
        TransacaoBuilder builder = new TransacaoBuilder();
        inicializarDadosPadroes(builder);
        return builder;
    }

    public static void inicializarDadosPadroes(TransacaoBuilder builder) {
        builder.elemento = new Transacao();
        Transacao elemento = builder.elemento;


        elemento.setId(1L);
        elemento.setDescricao("Transacao Valida");
        elemento.setValor(10.0);
        elemento.setConta(ContaBuilder.umaConta().agora());
        elemento.setData(LocalDate.now());
        elemento.setStatus(false);
    }

    public TransacaoBuilder comId(Long param) {
        elemento.setId(param);
        return this;
    }

    public TransacaoBuilder comDescricao(String param) {
        elemento.setDescricao(param);
        return this;
    }

    public TransacaoBuilder comValor(Double param) {
        elemento.setValor(param);
        return this;
    }

    public TransacaoBuilder comConta(Conta param) {
        elemento.setConta(param);
        return this;
    }

    public TransacaoBuilder comData(LocalDate param) {
        elemento.setData(param);
        return this;
    }

    public TransacaoBuilder comStatus(Boolean param) {
        elemento.setStatus(param);
        return this;
    }

    public Transacao agora() {
        return elemento;
    }
}