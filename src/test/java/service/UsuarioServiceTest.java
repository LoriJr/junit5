package service;

import com.viratech.domain.Usuario;
import com.viratech.service.UsuarioService;
import domain.builders.UsuarioBuilder;
import infra.UsuarioDummyRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UsuarioServiceTest {

    private UsuarioService service;

    @Test
    public void deveSalvarUsuarioComSucesso(){
        service = new UsuarioService(new UsuarioDummyRepository());
        Usuario usuario = UsuarioBuilder.umUsuario().comId(null).agora();
        Usuario savedUser = service.salvarUsuario(usuario);
        assertNotNull(savedUser.getId());

    }
}
