package Test;

import static org.junit.Assert.assertEquals;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import Clases.Viaje;
import ClasesDao.ViajeDAO;

public class BuscarViajeIntegrationTest {

    private ViajeDAO viajeDAO;
    private String filePath = "viajes_test.txt";

    @Before
    public void setUp() throws Exception {
        // Inicializar la instancia de ViajeDAO con un archivo de prueba
        viajeDAO = new ViajeDAO(filePath);
    }

    @After
    public void tearDown() throws Exception {
        // Limpiar el archivo de prueba después de cada prueba
        viajeDAO.setFilePath(filePath);
        viajeDAO.actualizarViajes(List.of());
    }

    @Test
    public void testBuscarYApuntarse() {
        // Crear un viaje de prueba
        Viaje viajePrueba = new Viaje("2024-06-01", "Ciudad A", "Ciudad B", "10:00", 20);

        // Guardar el viaje de prueba en el archivo
        boolean viajeGuardado = viajeDAO.guardarViaje(viajePrueba);
        assertEquals(true, viajeGuardado);

        // Buscar el viaje que acabamos de crear
        List<Viaje> viajesEncontrados = viajeDAO.buscarViajes("Ciudad A", "Ciudad B");
        assertEquals(1, viajesEncontrados.size());

        // Verificar que el viaje encontrado es el mismo que creamos
        Viaje viajeEncontrado = viajesEncontrados.get(0);
        assertEquals("2024-06-01", viajeEncontrado.getFecha());
        assertEquals("Ciudad A", viajeEncontrado.getOrigen());
        assertEquals("Ciudad B", viajeEncontrado.getDestino());
        assertEquals("10:00", viajeEncontrado.getHora());
        assertEquals(20, viajeEncontrado.getPlazasDisponibles());

        // Apuntarse al viaje encontrado
        boolean apuntarseExitoso = viajeDAO.apuntarseViaje(viajeEncontrado);
        assertEquals(true, apuntarseExitoso);
    }
}
