package service;

import com.viratech.domain.Conta;
import com.viratech.domain.Transacao;
import com.viratech.domain.exceptions.ValidationException;
import com.viratech.service.TransacaoService;
import com.viratech.service.external.ClockService;
import com.viratech.service.repositories.TransacaoDao;
import jdk.jfr.Enabled;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import static domain.builders.ContaBuilder.umaConta;
import static domain.builders.TransacaoBuilder.umTransacao;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Tag("service")
@Tag("transacao")
//@EnabledIf(value = "isHoraValida")
@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {

    @Mock
    private TransacaoDao dao;

    @Captor
    private ArgumentCaptor<Transacao> transacaoCaptor;

    @InjectMocks
    @Spy
    private TransacaoService service;

    @BeforeEach
    void setUp(){
        when(service.getTime()).thenReturn(LocalDateTime.of(2026, 8, 28, 10, 30, 20));
    }

    @Test
    public void deveSalvarTransacaoValida() {

        Transacao transacaoParaSalvar = umTransacao().comId(null).agora();
        when(dao.salvar(transacaoParaSalvar)).thenReturn(umTransacao().agora());

        Transacao transacaoSalva = service.salvar(transacaoParaSalvar);
        assertEquals(umTransacao().agora(), transacaoSalva);

        assertAll("Transação",
                () -> assertEquals(1L, transacaoSalva.getId()),
                () -> assertEquals("Transacao Valida", transacaoSalva.getDescricao()),
                () -> {
                    assertAll("Conta",
                            () -> assertEquals("Conta Válida", transacaoSalva.getConta().getNome()),
                            () -> {
                                assertAll("Usuário",
                                        () -> assertEquals("Usuario Valido", transacaoSalva.getConta().getUsuario().getNome()),
                                        () -> assertEquals("123456", transacaoSalva.getConta().getUsuario().getSenha()));
                            });
                });
    }


    @ParameterizedTest(name = "{6}")
    @MethodSource("dataProvider")
    public void deveRejeitarTransacao(Long id, String descricao, Double valor, LocalDate data, Conta conta, Boolean status, String mensagem){

        Transacao transacaoParaSalvar = umTransacao().comId(id).comDescricao(descricao).comValor(valor).comData(data).comConta(conta).comStatus(status).agora();
        ValidationException ex = assertThrows(ValidationException.class,
                ()-> service.salvar(transacaoParaSalvar));
        assertEquals(mensagem, ex.getMessage());
    }

    private static Stream<Arguments> dataProvider(){
        return Stream.of(
                Arguments.of(1L, null, 10.0, LocalDate.now(), umaConta().agora(), true, "Descrição inexistente"),
                Arguments.of(1L, "Transacao Valida", null, LocalDate.now(), umaConta().agora(), true, "Valor inexistente"),
                Arguments.of(1L, "Transacao Valida", 10.0, null, umaConta().agora(), true, "Data inexistente"),
                Arguments.of(1L, "Transacao Valida", 10.0, LocalDate.now(), null, true, "Conta inexistente")
        );
    }

    @Test
    public void deveRejeitarTransacaoTardeDaNoite(){

        when(service.getTime()).thenReturn(LocalDateTime.of(2026, 8, 28, 12, 30, 20));

        RuntimeException ex = assertThrows(RuntimeException.class,
                ()-> service.salvar(umTransacao().agora()));
        assertEquals("Tente novamente amanhã", ex.getMessage());
    }

    @Test
    public void deveAdicionarStatusFalsePadrao(){

        Transacao transacao = umTransacao().comStatus(null).agora();
        service.salvar(transacao);

        verify(dao).salvar(transacaoCaptor.capture());
        Transacao transacaoValida = transacaoCaptor.getValue();
        assertFalse(transacaoValida.getStatus());

    }

}
