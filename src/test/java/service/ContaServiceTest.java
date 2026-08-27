package service;

import com.viratech.domain.Conta;
import com.viratech.domain.exceptions.ValidationException;
import com.viratech.service.ContaService;
import com.viratech.service.enums.EventType;
import com.viratech.service.external.ContaEvent;
import com.viratech.service.repositories.ContaRepository;
import com.viratech.service.repositories.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static domain.builders.ContaBuilder.umaConta;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ContaServiceTest {

    @Mock ContaRepository contaRepository;
    @Mock ContaEvent evento;
    @InjectMocks ContaService contaService;

    @Test
    public void deveSalvarPrimeiraContaComSucesso(){

        Conta accountToSave = umaConta().comId(null).agora();

        when(contaRepository.salvar(accountToSave)).thenReturn(umaConta().agora());
        doNothing().when(evento).dispatch(umaConta().agora(), EventType.CREATED);

        Conta savedAccount = contaService.salvarConta(accountToSave);
        assertNotNull(savedAccount.getId());
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

//        when(contaRepository.salvar(accountToSave)).thenReturn(umaConta().agora());

        ValidationException ex = assertThrows(ValidationException.class,
                ()-> contaService.salvarConta(accountToSave));

        assertEquals(String.format("Já existe uma conta com esse nome", accountToSave.getNome()), ex.getMessage());
    }

    /*
    * Cenários para teste:
    * usuário sem conta
    * com conta não igual a que eu possuo
    * com conta igual a que eu possuo
    * */
}
