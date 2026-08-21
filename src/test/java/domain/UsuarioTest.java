package domain;

import com.viratech.domain.Usuario;
import com.viratech.domain.exceptions.ValidationException;
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
        return Stream.of(
                Arguments.of(1L, null, "usuario1@gmail", "123", "Nome é obrigatório"),
                Arguments.of(2L, "Usuario2", null, "123", "Email é obrigatório"),
                Arguments.of(3L, "Usuario3", "usuario1@gmail", null, "Senha é obrigatória")
        );
    }

    @Test
    public void deveCriarUsuario() {

        Usuario usuario = new Usuario(1L, "Usuario1", "emailUsuario1", "123");

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
}
