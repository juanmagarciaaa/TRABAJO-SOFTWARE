package Vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Clases.Usuario;
import ClasesDao.UsuarioDAO;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

public class frmRegistrarse extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    public JTextField lblIntroducirCorreo;
    public JPasswordField lblIntroducirContrasea;
    public JPasswordField lblContrasea2;
    public JButton btnRegistrar; // Esta es la definición correcta del botón

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frmRegistrarse frame = new frmRegistrarse();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public frmRegistrarse() {
        setResizable(false);
        setTitle("Traveling");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 930, 600);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblFoto = new JLabel("");
        lblFoto.setIcon(new ImageIcon(frmRegistrarse.class.getResource("/Componentes/fondoicono.jpg")));
        lblFoto.setForeground(Color.BLACK);
        lblFoto.setBounds(-308, 0, 767, 573);
        contentPane.add(lblFoto);
        
        JLabel lblRegistroUsuario = new JLabel("Crea Tu Cuenta");
        lblRegistroUsuario.setFont(new Font("Monospaced", Font.PLAIN, 25));
        lblRegistroUsuario.setBounds(582, 10, 225, 98);
        contentPane.add(lblRegistroUsuario);
        
        JLabel lblCorreo = new JLabel("Correo Electrónico:");
        lblCorreo.setFont(new Font("Monospaced", Font.PLAIN, 20));
        lblCorreo.setBounds(469, 98, 234, 51);
        contentPane.add(lblCorreo);
        
        lblIntroducirCorreo = new JTextField();
        lblIntroducirCorreo.setFont(new Font("Monospaced", Font.PLAIN, 15));
        lblIntroducirCorreo.setBounds(479, 156, 427, 51);
        contentPane.add(lblIntroducirCorreo);
        lblIntroducirCorreo.setColumns(10);
        
        JLabel lblContrasea = new JLabel("Contraseña:");
        lblContrasea.setFont(new Font("Monospaced", Font.PLAIN, 20));
        lblContrasea.setBounds(469, 217, 234, 51);
        contentPane.add(lblContrasea);
        
        lblIntroducirContrasea = new JPasswordField();
        lblIntroducirContrasea.setForeground(Color.BLACK);
        lblIntroducirContrasea.setBounds(479, 272, 427, 51);
        contentPane.add(lblIntroducirContrasea);
        
        JLabel lblVuelvaAIntroducir = new JLabel("Repita la contraseña:");
        lblVuelvaAIntroducir.setFont(new Font("Monospaced", Font.PLAIN, 20));
        lblVuelvaAIntroducir.setBounds(469, 333, 252, 51);
        contentPane.add(lblVuelvaAIntroducir);
        
        lblContrasea2 = new JPasswordField();
        lblContrasea2.setForeground(Color.BLACK);
        lblContrasea2.setBounds(479, 382, 427, 51);
        contentPane.add(lblContrasea2);
        
        JButton btnIniciarSesion = new JButton("Iniciar Sesion");
        btnIniciarSesion.setFont(new Font("Monospaced", Font.PLAIN, 12));
        btnIniciarSesion.setBounds(469, 478, 139, 51);
        contentPane.add(btnIniciarSesion);
        
        JLabel lblPregunta = new JLabel("¿Ya estás registrado?");
        lblPregunta.setFont(new Font("Monospaced", Font.PLAIN, 12));
        lblPregunta.setBounds(469, 454, 151, 19);
        contentPane.add(lblPregunta);
        
        btnRegistrar = new JButton("Registrarse"); // Asegúrate de no declarar de nuevo el botón aquí
        btnRegistrar.setFont(new Font("Monospaced", Font.PLAIN, 14));
        btnRegistrar.setBounds(719, 478, 187, 51);
        contentPane.add(btnRegistrar);
        
        JCheckBox chckbxMostrarContrasea = new JCheckBox("Mostrar Contraseña");
        chckbxMostrarContrasea.setFont(new Font("Monospaced", Font.PLAIN, 10));
        chckbxMostrarContrasea.setBounds(755, 439, 151, 21);
        contentPane.add(chckbxMostrarContrasea);

        btnRegistrar.addActionListener(e -> registrarUsuario());
        chckbxMostrarContrasea.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    lblIntroducirContrasea.setEchoChar((char) 0);
                    lblContrasea2.setEchoChar((char) 0);
                } else {
                    lblIntroducirContrasea.setEchoChar('*');
                    lblContrasea2.setEchoChar('*');
                }
            }
        });
        btnIniciarSesion.addActionListener(e -> {
            frmIniciarSesion loginFrame = new frmIniciarSesion();
            loginFrame.setVisible(true);
            this.dispose(); // Close the current frame
        });
    }
    
    private void registrarUsuario() {
        String correo = lblIntroducirCorreo.getText();
        char[] contrasena = lblIntroducirContrasea.getPassword();
        char[] contrasena2 = lblContrasea2.getPassword();

        // Comprobar si las contraseñas son iguales
        if (Arrays.equals(contrasena, contrasena2)) {
            Usuario nuevoUsuario = new Usuario("DNI123", "Nombre", "Apellido", correo, 123456789, null, null, null, null, new String(contrasena));

            // Guardar usuario en archivo de texto
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.addUsuario(nuevoUsuario);

            JOptionPane.showMessageDialog(this, "Usuario registrado exitosamente.");

            // Limpiar los campos
            lblIntroducirCorreo.setText("");
            lblIntroducirContrasea.setText("");
            lblContrasea2.setText("");
            frmIniciarSesion loginFrame = new frmIniciarSesion();
            loginFrame.setVisible(true);
            this.dispose(); //cerramos actual
        } else {
            // Si no son iguales, mostrar un mensaje de error
            JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden. Inténtalo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);

            // Opcional: limpiar los campos de contraseña
            lblIntroducirContrasea.setText("");
            lblContrasea2.setText("");
        }
    }
}
