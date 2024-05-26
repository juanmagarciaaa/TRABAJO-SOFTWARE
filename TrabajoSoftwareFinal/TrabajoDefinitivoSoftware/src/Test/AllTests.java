package Test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    UsuarioLogicaTest.class, ViajeDAOTest.class, UsuarioDAOTest.class, FrmCrearViajeIntegrationTest.class, BuscarViajeIntegrationTest.class,
    FrmIniciarSesionIntegrationTest.class, FrmRegistrarseIntegrationTest.class
})
public class AllTests {
    
}
