import com.viratech.Calculadora;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculadoraTest {

    @Test
    public void deveSomar(){
        Calculadora calculadora = new Calculadora();

        int resultado = calculadora.soma(2,5);

        assertEquals(7, resultado);
    }
}
