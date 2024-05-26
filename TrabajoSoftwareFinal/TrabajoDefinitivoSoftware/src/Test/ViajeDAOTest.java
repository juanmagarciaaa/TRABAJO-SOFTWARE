package Test;

import Clases.Viaje;
import ClasesDao.ViajeDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ViajeDAOTest {

    private static final String TEST_FILE_PATH = "test_viajes.txt";

    @Before
    public void setUp() throws IOException {
        // Crear un archivo de prueba con algunos viajes
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(TEST_FILE_PATH))) {
            bw.write("01-01-2025, Ciudad A, Ciudad B, 08:00, 5\n");
            bw.write("02-01-2025, Ciudad C, Ciudad D, 10:00, 10\n");
            bw.write("03-01-2025, Ciudad E, Ciudad F, 12:00, 3\n");
        }
    }

    @After
    public void tearDown() {
        // Eliminar el archivo de prueba después de cada prueba
        try {
            java.nio.file.Files.deleteIfExists(java.nio.file.Paths.get(TEST_FILE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCargarViajes() {
        ViajeDAO viajeDAO = new ViajeDAO(TEST_FILE_PATH);
        List<Viaje> viajes = viajeDAO.cargarViajes();
        assertEquals(3, viajes.size());
    }

    @Test
    public void testGuardarViaje() {
        ViajeDAO viajeDAO = new ViajeDAO(TEST_FILE_PATH);
        Viaje nuevoViaje = new Viaje("04-01-2025", "Ciudad G", "Ciudad H", "14:00", 7);
        boolean resultado = viajeDAO.guardarViaje(nuevoViaje);
        assertTrue(resultado);

        List<Viaje> viajes = viajeDAO.cargarViajes();
        assertEquals(4, viajes.size());
    }

    @Test
    public void testBuscarViajes() {
        ViajeDAO viajeDAO = new ViajeDAO(TEST_FILE_PATH);
        List<Viaje> viajesEncontrados = viajeDAO.buscarViajes("Ciudad C", "Ciudad D");
        assertEquals(1, viajesEncontrados.size());
        assertEquals("02-01-2025", viajesEncontrados.get(0).getFecha());
        assertEquals("Ciudad C", viajesEncontrados.get(0).getOrigen());
        assertEquals("Ciudad D", viajesEncontrados.get(0).getDestino());
    }

    @Test
    public void testApuntarseViaje() {
        ViajeDAO viajeDAO = new ViajeDAO(TEST_FILE_PATH);
        Viaje viajeExistente = new Viaje("01-01-2025", "Ciudad A", "Ciudad B", "08:00", 5);
        boolean resultado = viajeDAO.apuntarseViaje(viajeExistente);
        assertTrue(resultado);

        List<Viaje> viajes = viajeDAO.cargarViajes();
        Viaje viajeActualizado = viajes.stream()
                .filter(v -> v.getFecha().equals("01-01-2025") && v.getOrigen().equals("Ciudad A")
                        && v.getDestino().equals("Ciudad B") && v.getHora().equals("08:00"))
                .findFirst().orElse(null);
        assertEquals(4, viajeActualizado.getPlazasDisponibles());
    }

    @Test
    public void testActualizarViajes() {
        ViajeDAO viajeDAO = new ViajeDAO(TEST_FILE_PATH);
        List<Viaje> viajes = viajeDAO.cargarViajes();
        viajes.get(0).setPlazasDisponibles(2); // Reducir las plazas disponibles

        viajeDAO.actualizarViajes(viajes);

        List<Viaje> viajesActualizados = viajeDAO.cargarViajes();
        assertEquals(2, viajesActualizados.get(0).getPlazasDisponibles());
    }
}
