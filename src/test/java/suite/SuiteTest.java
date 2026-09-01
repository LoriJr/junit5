package suite;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

//@Suite para usar as tags e não rodar a suite completa então comenta essa linha para pegar somente os testes sem a suite e rodar o mvn test -Dgroups=usuario no terminal
@SuiteDisplayName("Suite de Testes")
@SelectPackages(value = {
        "service",
        "domain"
})


//@SelectClasses(value = {
//        UsuarioTest.class,
//        UsuarioServiceTest.class,
//        ContaServiceTest.class
//})

public class SuiteTest {
}
