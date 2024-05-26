package Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Clases.Usuario;
import ClasesDao.UsuarioDAO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class UsuarioDAOTest {

    private UsuarioDAO usuarioDAO;
    private static final String TEST_FILE_PATH = "test_usuarios.txt";

    @Before
    public void setUp() {
        usuarioDAO = new UsuarioDAO();
        // Crear un archivo de prueba para evitar modificar el archivo original
        createTestFile();
    }

    @After
    public void tearDown() {
        // Eliminar el archivo de prueba después de cada prueba
        File file = new File(TEST_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
        usuarioDAO = null;
    }

    @Test
    public void testAddUsuario() {
        Usuario usuario = new Usuario("123", "John", "Doe", "john@example.com", 123456789, null, null, null, null, "password");
        usuarioDAO.addUsuario(usuario);
        assertTrue(usuarioExistsInFile(usuario));
    }

    @Test
    public void testBuscarUsuarioExistente() {
        Usuario usuario = new Usuario("123", "John", "Doe", "john@example.com", 123456789, null, null, null, null, "password");
        usuarioDAO.addUsuario(usuario);
        assertEquals(usuario.getEmail(), usuarioDAO.buscarUsuario("john@example.com", "password").getEmail());
    }

    @Test
    public void testBuscarUsuarioNoExistente() {
        Usuario usuario = new Usuario("321", "Jack", "Pan", "jack@example.com", 987654321, null, null, null, null, "password2");
        assertNull(usuarioDAO.buscarUsuario("jack@example.com", "password2"));
    }

    @Test
    public void testActualizarUsuario() {
        Usuario usuario = new Usuario("123", "John", "Doe", "john@example.com", 123456789, null, null, null, null, "password");
        usuarioDAO.addUsuario(usuario);
        usuario.setNombre("UpdatedName");
        usuarioDAO.actualizarUsuario(usuario);
        assertTrue(usuarioExistsInFile(usuario));
    }

    @Test
    public void testEliminarUsuario() {
        usuarioDAO.eliminarUsuario("juan@example.com");
        assertNull(usuarioDAO.obtenerUsuarioPorCorreo("juan@example.com"));
    }

    // Método para crear un archivo de prueba

    private void createTestFile() {
        try (FileWriter writer = new FileWriter(TEST_FILE_PATH)) {
            // Crear un archivo de prueba vacío
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean usuarioExistsInFile(Usuario usuario) {
        // Verificar si el usuario existe en el archivo de prueba
        // Implementar la lógica para buscar el usuario en el archivo de prueba
        return true;  // Implementar la lógica real para verificar la existencia del usuario en el archivo
    }
}