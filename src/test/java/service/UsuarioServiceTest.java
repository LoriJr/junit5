package service;

import com.viratech.domain.Usuario;
import com.viratech.service.UsuarioService;
import com.viratech.service.repositories.UsuarioRepository;
import domain.builders.UsuarioBuilder;
import infra.UsuarioDummyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioServiceTest {

    private UsuarioService service;

    @BeforeEach
    void setUp(){
        UsuarioRepository repository = Mockito.mock(UsuarioRepository.class);
        service = new UsuarioService(repository);
    }

    @Test
    public void deveRetornarUsuarioPorEmail(){
       Optional<Usuario> usuario = service.getUserByEmail("email@gmail.com");
       assertTrue(usuario.isEmpty());
    }
}
