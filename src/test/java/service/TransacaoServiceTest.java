package service;

import com.viratech.domain.Transacao;
import com.viratech.service.TransacaoService;
import com.viratech.service.repositories.TransacaoDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static domain.builders.TransacaoBuilder.umTransacao;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {

    @Mock
    private TransacaoDao dao;

    @InjectMocks
    private TransacaoService service;

    @Test
    public void deveSalvarTransacaoValida(){

        Transacao transacaoParaSalvar = umTransacao().comId(null).agora();
        when(dao.salvar(transacaoParaSalvar)).thenReturn(umTransacao().agora());

        Transacao transacaoSalva = service.salvar(transacaoParaSalvar);
        assertEquals(umTransacao().agora(), transacaoSalva);

    }
}
