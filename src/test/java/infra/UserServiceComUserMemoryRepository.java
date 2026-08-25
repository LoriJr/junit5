package infra;

import com.viratech.domain.Usuario;
import com.viratech.domain.exceptions.ValidationException;
import com.viratech.infra.UsuarioMemoryRepository;
import com.viratech.service.UsuarioService;
import domain.builders.UsuarioBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceComUserMemoryRepository {

    private UsuarioService service = new UsuarioService(new UsuarioMemoryRepository());

    @Test
    public void deveSalvarUsuarioValido(){
        Usuario usuario = service.salvarUsuario(UsuarioBuilder.umUsuario().comId(null).comEmail("email@gmail.com").agora());
        assertNotNull(usuario.getId());
    }

    @Test
    public void deveRejeitarUsuarioExistente(){

        Usuario usuario = UsuarioBuilder.umUsuario().comId(null).comEmail("email@gmail.com").agora();

        ValidationException ex = assertThrows(ValidationException.class,
                ()-> service.salvarUsuario(usuario));
        assertEquals(String.format("Existe um cadastro com o email %s", usuario.getEmail()), ex.getMessage());
    }
}
