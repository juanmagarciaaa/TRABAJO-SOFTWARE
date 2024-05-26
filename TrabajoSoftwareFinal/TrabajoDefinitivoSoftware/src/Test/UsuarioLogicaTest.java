package Test;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import Logica.UsuarioLogica;
import Clases.Usuario;
import ClasesDao.UsuarioDAO;

public class UsuarioLogicaTest {
    private UsuarioLogica usuarioLogica;

    @Before
    public void setUp() throws Exception {
        usuarioLogica = new UsuarioLogica();
        // Cargar usuarios de prueba
        UsuarioDAO.cargarUsuarios();
    }

    @Test
    public void testEsUsuarioNuevo() {
        Usuario usuarioNuevo = new Usuario(null, null, null, "nuevo@example.com", 0, null, null, null, null, null);
        assertTrue(usuarioLogica.esUsuarioNuevo(usuarioNuevo));
    }

    @Test
    public void testNoEsUsuarioNuevo() {
        Usuario usuarioExistente = new Usuario("123456789A", "Juan", "Perez", "juan@example.com", 123456789, null, null, null, null, "password");
        assertFalse(usuarioLogica.esUsuarioNuevo(usuarioExistente));
    }
}
