package suite;

import domain.UsuarioTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;
import service.ContaServiceTest;
import service.UsuarioServiceTest;

@Suite
@SuiteDisplayName("Suite de Testes")
@SelectClasses(value = {
        UsuarioTest.class,
        UsuarioServiceTest.class,
        ContaServiceTest.class
})

public class SuiteTest {
}
