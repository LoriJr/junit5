package service;

import com.viratech.domain.Conta;
import com.viratech.service.ContaService;
import com.viratech.service.repositories.ContaRepository;
import com.viratech.service.repositories.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static domain.builders.ContaBuilder.umaConta;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ContaServiceTest {

    @Mock ContaRepository contaRepository;
    @Mock UsuarioRepository usuarioRepository;

    @InjectMocks ContaService contaService;

    @Test
    public void deveSalvarConta(){

        Conta accountToSave = umaConta().comId(null).agora();

        when(contaRepository.salvar(accountToSave)).thenReturn(umaConta().agora());

        Conta savedAccount = contaService.salvarConta(accountToSave);
        assertNotNull(savedAccount.getId());
    }
}
