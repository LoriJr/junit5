package service;

import com.viratech.domain.Conta;
import com.viratech.domain.exceptions.ErrorSendNotification;
import com.viratech.domain.exceptions.ValidationException;
import com.viratech.service.ContaService;
import com.viratech.service.enums.EventType;
import com.viratech.service.external.ContaEvent;
import com.viratech.service.repositories.ContaRepository;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static domain.builders.ContaBuilder.umaConta;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Tag("service")
@Tag("conta")
@ExtendWith(MockitoExtension.class)
public class ContaServiceTest {

    @Mock ContaRepository contaRepository;
    @Mock ContaEvent evento;
    @InjectMocks ContaService contaService;

    @Captor ArgumentCaptor<Conta> contaCaptor;

    @Test
    public void deveSalvarPrimeiraContaComSucesso(){

        Conta accountToSave = umaConta().comId(null).agora();

        when(contaRepository.salvar(any(Conta.class))).thenReturn(umaConta().agora());
        doNothing().when(evento).dispatch(umaConta().agora(), EventType.CREATED);

        Conta savedAccount = contaService.salvarConta(accountToSave);
        assertNotNull(savedAccount.getId());

        verify(contaRepository).salvar(contaCaptor.capture());
    }

    @Test
    public void deveSalvarASegundaContaComSucesso(){

        Conta accountToSave = umaConta().comId(null).agora();

        when(contaRepository.obterContasPorUsuario(accountToSave.getUsuario().getId()))
                .thenReturn(Arrays.asList(umaConta().comNome("Teste Conta").agora()));

        when(contaRepository.salvar(accountToSave)).thenReturn(umaConta().agora());

        Conta savedAccount = contaService.salvarConta(accountToSave);
        assertNotNull(savedAccount.getId());
    }


    @Test
    public void deveRejeitarContaRepetida(){

        Conta accountToSave = umaConta().comId(null).agora();

        when(contaRepository.obterContasPorUsuario(accountToSave.getUsuario().getId()))
                .thenReturn(Arrays.asList(umaConta().agora()));

        ValidationException ex = assertThrows(ValidationException.class,
                ()-> contaService.salvarConta(accountToSave));

        assertEquals(String.format("Já existe uma conta com esse nome %s", accountToSave.getNome()), ex.getMessage());
    }

    @Test
    public void naoDeveManterContaSemEvento() {
        Conta accountToSave = umaConta().comId(null).agora();
        Conta accountSaved = umaConta().agora();

        when(contaRepository.salvar(accountToSave)).thenReturn(accountSaved);
        doThrow(new ErrorSendNotification("Falha no envio de notificação"))
                .when(evento).dispatch(accountSaved, EventType.CREATED);

       String mensagem = assertThrows(ErrorSendNotification.class,
               ()-> contaService.salvarConta(accountToSave)).getMessage();
       assertEquals("Falha no envio de notificação", mensagem);

    }
}
