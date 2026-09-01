package domain;

import com.viratech.domain.Usuario;
import com.viratech.domain.exceptions.ValidationException;
import domain.builders.UsuarioBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@Tag("dominio")
@Tag("usuario")
@DisplayName("Domínio: Usuario")
public class UsuarioTest {

    @Test
    public void deveCriarUsuario() {

        Usuario usuario = UsuarioBuilder.umUsuario().agora();

        assertAll(
                () -> assertNotNull(usuario.getNome()),
                () -> assertNotNull(usuario.getEmail()),
                () -> assertNotNull(usuario.getSenha())
        );
    }

    @ParameterizedTest(name = "{4}")
    @CsvSource(value = {
            "1, NULL, usuario1@gmail, 123, Nome é obrigatório",
            "2, Usuario2, NULL, 123, Email é obrigatório",
            "3, Usuario3, usuario1@gmail, NULL, Senha é obrigatória",
    }, nullValues = "NULL")
    public void deveLancarExcecaoCampoVazio(Long id, String nome, String email, String senha, String mensagem) {

        Exception exception = assertThrows(ValidationException.class,
                ()-> UsuarioBuilder.umUsuario().comId(id).comNome(nome).comEmail(email).comSenha(senha).agora()
        );
        assertEquals(mensagem, exception.getMessage());
    }
}
