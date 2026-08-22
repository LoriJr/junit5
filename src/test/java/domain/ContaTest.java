package domain;

import com.viratech.domain.Conta;
import com.viratech.domain.Usuario;
import com.viratech.domain.exceptions.ValidationException;
import domain.builders.ContaBuilder;
import domain.builders.UsuarioBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    //demanda: criar um comportamento de teste para validar preechime de nome e usuário
    @ParameterizedTest(name = "{3}")
    @MethodSource(value = "dataProvider")
    public void deveRejeitarContaInvalida(Long id, String nome, Usuario usuario, String mensagem){
        ValidationException ex  = Assertions.assertThrows(ValidationException.class,
                ()-> ContaBuilder.umaConta().comId(id).comNome(nome).comUsuario(usuario).agora());
        assertEquals(mensagem, ex.getMessage());
    }

    private static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.of(1L, null, UsuarioBuilder.umUsuario().agora(), "Nome é obrigatório"),
                Arguments.of(1L, "Conta Válida", null, "Usuário é obrigatório"
                ));
    }
}
