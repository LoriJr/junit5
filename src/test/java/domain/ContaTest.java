package domain;

import com.viratech.domain.Conta;
import domain.builders.ContaBuilder;
import domain.builders.UsuarioBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContaTest {

    @Test
    public void deveCriarContaValida(){
        //Criar conta
        Conta conta = ContaBuilder.umaConta().agora();
        //Assertivas em cima da conta
        assertAll(
                ()->assertEquals(1L, conta.getId()),
                ()->assertEquals("Conta Válida", conta.getNome()),
                ()->assertEquals(UsuarioBuilder.umUsuario().agora(), conta.getUsuario())
        );
    }
}
