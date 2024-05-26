package Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Clases.Usuario;
import ClasesDao.UsuarioDAO;
import Vistas.frmRegistrarse;

public class FrmRegistrarseIntegrationTest {

    private frmRegistrarse frame;
    private UsuarioDAO usuarioDAO;

    @Before
    public void setUp() {
        frame = new frmRegistrarse();
        frame.setVisible(false);  // No mostrar la GUI durante las pruebas
        usuarioDAO = new UsuarioDAO();
    }

    @After
    public void tearDown() {
        frame.dispose();  // Cerrar el frame después de cada prueba
        // Limpiar los usuarios creados durante la prueba
        Usuario usuario = usuarioDAO.buscarUsuario("test@example.com", "password123");
        if (usuario != null) {
            usuarioDAO.eliminarUsuario(usuario.getEmail());
        }
    }

    @Test
    public void testRegistrarUsuario() {
        // Verificar que los componentes no sean null
        assertNotNull("lblIntroducirCorreo es null", frame.lblIntroducirCorreo);
        assertNotNull("lblIntroducirContrasea es null", frame.lblIntroducirContrasea);
        assertNotNull("lblContrasea2 es null", frame.lblContrasea2);
        assertNotNull("btnRegistrar es null", frame.btnRegistrar);

        // Configurar los campos de texto del formulario
        frame.lblIntroducirCorreo.setText("test@example.com");
        frame.lblIntroducirContrasea.setText("password123");
        frame.lblContrasea2.setText("password123");

        // Simular clic en el botón de registro
        frame.btnRegistrar.doClick();

        // Verificar que el usuario fue registrado correctamente
        Usuario usuarioGuardado = usuarioDAO.buscarUsuario("test@example.com", "password123");
        assertNotNull(usuarioGuardado);
        assertEquals("test@example.com", usuarioGuardado.getEmail());

        // Eliminar el usuario registrado
        usuarioDAO.eliminarUsuario("test@example.com");

        // Verificar que el usuario fue eliminado
        Usuario usuarioEliminado = usuarioDAO.buscarUsuario("test@example.com", "password123");
        assertNull(usuarioEliminado);
    }

    // Agrega aquí más pruebas de integración según sea necesario
}
