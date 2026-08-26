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
import static org.mockito.Mockito.*;

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

        Optional<Usuario> resultado = service.getUserByEmail("email@gmail.com");

        assertTrue(resultado.isPresent());
        assertEquals(usuario, resultado.get());

        //verifico que o repositório ao chamar o getUserByEmail confirme que ela foi realizada com esse email
        verify(repository).getUserByEmail("email@gmail.com");

        //verifico que o repositório ao chamar o método x, ele foi chamado somente uma vez
        verify(repository, times(1)).getUserByEmail("email@gmail.com");

        //verifico que o repositório tenha chamdo pelo menos uma chamada no método x com esse email
        verify(repository, atLeastOnce()).getUserByEmail("email@gmail.com");

        //verifico que o repositório tenha chamado no mínimo 5 vezes
        verify(repository, atLeast(5)).getUserByEmail("email@gmail.com");

        //verifico que o repositório ao chamar o método x, nunca tenha sido com esse email
        verify(repository, never()).getUserByEmail("email@teste");




    }
}
