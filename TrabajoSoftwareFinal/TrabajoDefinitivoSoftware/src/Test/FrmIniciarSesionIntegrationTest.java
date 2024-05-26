package Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.AWTException;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Clases.Usuario;
import ClasesDao.UsuarioDAO;
import Vistas.frmIniciarSesion;

public class FrmIniciarSesionIntegrationTest {

    private frmIniciarSesion frame;
    private JTextField txtCorreo;
    private JPasswordField txtContrasena;
    private JButton btnIniciarSesion;
    private Usuario usuario; // Nuevo usuario para el test

    @Before
    public void setUp() throws Exception {
        usuario = new Usuario("123456789", "John", "Doe", "john@example.com",
                              123456789, null, null, null, null, "password");
        UsuarioDAO.guardarUsuario(usuario);
        frame = new frmIniciarSesion();
        frame.setVisible(true);

        // Acceder a los campos de texto y botón desde el formulario
        txtCorreo = frame.textField;
        txtContrasena = frame.passwordField;
        btnIniciarSesion = frame.btnIniciarSesion;
    }

    @After
    public void tearDown() throws Exception {
        frame.dispose();
    }

    @Test
    public void testIniciarSesionConCredencialesCorrectas() throws AWTException {
        assertNotNull(frame);
        assertNotNull(txtCorreo);
        assertNotNull(txtContrasena);
        assertNotNull(btnIniciarSesion);
        
        // Simulación de ingreso de datos
        String correo = usuario.getEmail();
        String contrasena = usuario.getContrasena();

        // Llenar campos
        txtCorreo.setText(correo);
        txtContrasena.setText(contrasena);

        // Hacer clic en el botón de iniciar sesión
        btnIniciarSesion.doClick();

        // Pausa para permitir la ejecución de la acción del botón
        try {
            Thread.sleep(2000); // 2 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verificación de la salida esperada después de iniciar sesión
        // Por ejemplo, podrías verificar si se abrió el formulario principal después de iniciar sesión
        // Aquí estoy verificando si el formulario de inicio de sesión ya no está visible
        assertFalse(frame.isVisible());
    }

    @Test
    public void testIniciarSesionConCredencialesIncorrectas() throws AWTException {
        assertNotNull(frame);
        assertNotNull(txtCorreo);
        assertNotNull(txtContrasena);
        assertNotNull(btnIniciarSesion);

        // Simulación de ingreso de datos incorrectos
        String correoIncorrecto = "usuario@example.com";
        String contrasenaIncorrecta = "contrasenaincorrecta";

        // Llenar campos
        txtCorreo.setText(correoIncorrecto);
        txtContrasena.setText(contrasenaIncorrecta);

        // Hacer clic en el botón de iniciar sesión
        btnIniciarSesion.doClick();

        // Pausa para permitir la ejecución de la acción del botón
        try {
            Thread.sleep(2000); // 2 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verificación de la salida esperada después de iniciar sesión
        // Aquí podrías verificar si se mostró un mensaje de error o se limpiaron los campos de texto
        // En este ejemplo, verifico que el formulario de inicio de sesión todavía esté visible
        assertTrue(frame.isVisible());
    }
}
