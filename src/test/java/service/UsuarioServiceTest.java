package service;

import com.viratech.domain.Usuario;
import com.viratech.service.UsuarioService;
import com.viratech.service.repositories.UsuarioRepository;
import domain.builders.UsuarioBuilder;
import infra.UsuarioDummyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    private UsuarioService service;

    @Test
    public void deveRetornarEmptyQuandoUsuarioInexistente(){
       Optional<Usuario> usuario = service.getUserByEmail("email@gmail.com");
       assertTrue(usuario.isEmpty());
    }

    @Test
    public void deveRetonarUsuarioComSucesso(){

        Usuario usuario = UsuarioBuilder.umUsuario().agora();

        when(repository.getUserByEmail("email@gmail.com"))
                .thenReturn(Optional.of(usuario));

        Optional<Usuario> resultado =
                service.getUserByEmail("email@gmail.com");

        assertTrue(resultado.isPresent());
        assertEquals(usuario, resultado.get());
    }
}
