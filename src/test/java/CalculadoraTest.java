import com.viratech.Calculadora;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraTest {

    @Test
    public void deveSomar(){
        Calculadora calculadora = new Calculadora();

        int resultado = calculadora.soma(2,5);

        assertEquals(7, resultado);
        assertTrue(calculadora.soma(2,5) == 7);
    }

    @Test
    public void assertivas(){
        assertEquals("Casa", "Casa");
        assertNotEquals("Casa", "casa");
        assertTrue("casa".equalsIgnoreCase("CASA"));
        assertTrue("Casa".endsWith("sa"));
        assertTrue("Casa".startsWith("Ca"));

        List<String> s1 = new ArrayList<>();
        List<String> s2 = new ArrayList<>();
        List<String> s3 = null;

        assertEquals(s1, s2);
        assertSame(s1, s1); //vai olhar se é exatamente a mesma referência
        assertNotEquals(s1,s3);
        assertNull(s3);
        assertNotNull(s1);
//        fail("Falhou por nenhum motivo"); // serve somente para adicionar uma falha
    }
}
