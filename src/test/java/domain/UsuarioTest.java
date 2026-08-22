package domain;

import com.viratech.domain.Usuario;
import com.viratech.domain.exceptions.ValidationException;
import domain.builders.UsuarioBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Domínio: Usuario")
public class UsuarioTest {


    static Stream<Arguments> getParametros() {
        /*
        Um exemplo usando o builder para o nome do usuário com valor nulo, o problema de adicionar esse teste no ParameterizedTest, é que ele seria inicializado assim que a classe carregasse, mas como o Usuário será carregado no construtor, e nesse caso vindo com o nome nulo, seria lançado direto uma exceção de validação que está na entidade Usuario, onde nesse caso o teste não seria executado devido essa exceção lançada previamente na inicialização da classe de teste.
        */

        return Stream.of(
//*nota logo acima Arguments.of(UsuarioBuilder.umUsuario().comNome(null).agora(), "Nome é obrigatório"),
                Arguments.of(1L, null, "usuario1@gmail", "123", "Nome é obrigatório"),
                Arguments.of(2L, "Usuario2", null, "123", "Email é obrigatório"),
                Arguments.of(3L, "Usuario3", "usuario1@gmail", null, "Senha é obrigatória")
        );
    }

    @Test
    public void deveCriarUsuario() {

        Usuario usuario = UsuarioBuilder.umUsuario().agora(); // usando o builder para construir o usuário

        assertAll(
                () -> assertNotNull(usuario.getNome()),
                () -> assertNotNull(usuario.getEmail()),
                () -> assertNotNull(usuario.getSenha())
        );
    }

    @ParameterizedTest(name="{4}")
    @MethodSource("getParametros")
    public void deveLancarExcecaoCampoVazio(
            Long id,
            String nome,
            String email,
            String senha,
            String mensagem) {


        Exception exception = assertThrows(ValidationException.class,
                ()-> new Usuario(id, nome, email, senha)
        );
        assertEquals(mensagem, exception.getMessage());

    }

    /* teste exemplo de como seria a exceção usando o UsuarioBuilder */
    @Test
    public void deveLancarExcecaoEmailNulo(){
        Exception ex = assertThrows(ValidationException.class,
                ()-> UsuarioBuilder.umUsuario().comEmail(null).agora());
        assertEquals("Email é obrigatório", ex.getMessage());
    }
}
