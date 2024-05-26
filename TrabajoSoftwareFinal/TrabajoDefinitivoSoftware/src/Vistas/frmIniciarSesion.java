package Vistas;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Logica.UsuarioLogica;
import Clases.Usuario;
import ClasesDao.UsuarioDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frmIniciarSesion extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    public JTextField textField;
    public JPasswordField passwordField;
    private JCheckBox chckbxMostrarContrasea;
	public JButton btnIniciarSesion;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UsuarioDAO.cargarUsuarios();  // Cargar usuarios al inicio de la aplicación
                    frmIniciarSesion frame = new frmIniciarSesion();
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
    public frmIniciarSesion() {
        setTitle("Traveling");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 930, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setLocationRelativeTo(null);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel contentPane_1 = new JPanel();
        contentPane_1.setBounds(-21, 0, 1048, 553);
        contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane_1.setBackground(Color.WHITE);
        contentPane.add(contentPane_1);
        contentPane_1.setLayout(null);

        JLabel lblFoto = new JLabel("");
        lblFoto.setIcon(new ImageIcon(frmIniciarSesion.class.getResource("/Componentes/fondoicono.jpg")));
        lblFoto.setBounds(-296, 0, 726, 573);
        lblFoto.setForeground(Color.BLACK);
        contentPane_1.add(lblFoto);

        JLabel lblInicioSesion = new JLabel("Inicia Sesión");
        lblInicioSesion.setBounds(582, 10, 225, 98);
        lblInicioSesion.setFont(new Font("Monospaced", Font.PLAIN, 25));
        contentPane_1.add(lblInicioSesion);

        JLabel lblCorreo = new JLabel("Correo Electrónico:");
        lblCorreo.setBounds(469, 98, 234, 51);
        lblCorreo.setFont(new Font("Monospaced", Font.PLAIN, 20));
        contentPane_1.add(lblCorreo);

        textField = new JTextField();
        textField.setBounds(479, 156, 427, 51);
        textField.setFont(new Font("Monospaced", Font.PLAIN, 15));
        textField.setColumns(10);
        contentPane_1.add(textField);

        JLabel lblContrasea = new JLabel("Contraseña:");
        lblContrasea.setBounds(469, 217, 234, 51);
        lblContrasea.setFont(new Font("Monospaced", Font.PLAIN, 20));
        contentPane_1.add(lblContrasea);

        passwordField = new JPasswordField();
        passwordField.setBounds(479, 272, 427, 51);
        passwordField.setForeground(Color.BLACK);
        contentPane_1.add(passwordField);

         btnIniciarSesion = new JButton("Iniciar Sesion");
        
        	btnIniciarSesion.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    UsuarioLogica.cargarUsuarios();
                    String email = textField.getText();
                    String contrasena = new String(passwordField.getPassword());
                    Usuario usuario = UsuarioLogica.autentificar(email, contrasena);

                    if (usuario != null) {
                        if (UsuarioLogica.esUsuarioNuevo(usuario)) {
                            frmCuestionario frmCuestionario = new frmCuestionario(usuario);
                            frmCuestionario.setVisible(true);
                            dispose();
                           
                        } else {
                            JOptionPane.showMessageDialog(null, "Bienvenido a Traveling");
                            frmMenuPrincipal frmMenuPrincipal= new frmMenuPrincipal();
                            frmMenuPrincipal.setVisible(true);
                            dispose();
                        }
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Correo o contraseña incorrecto");
                    }
                }
            });
        
        btnIniciarSesion.setBounds(769, 400, 139, 51);
        btnIniciarSesion.setFont(new Font("Monospaced", Font.PLAIN, 12));
        contentPane_1.add(btnIniciarSesion);

        JLabel lblanNoTiene = new JLabel("¿Aún no tiene cuenta?");
        lblanNoTiene.setBounds(485, 451, 151, 19);
        lblanNoTiene.setFont(new Font("Monospaced", Font.PLAIN, 12));
        contentPane_1.add(lblanNoTiene);

        JButton btnRegistrar = new JButton("Registrarse");
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frmRegistrarse frmRegistrarse = new frmRegistrarse();
                frmRegistrarse.setVisible(true);
                dispose();
            }
        });
        btnRegistrar.setBounds(469, 480, 187, 51);
        btnRegistrar.setFont(new Font("Monospaced", Font.PLAIN, 14));
        contentPane_1.add(btnRegistrar);

        chckbxMostrarContrasea = new JCheckBox("Mostrar Contraseña");
        chckbxMostrarContrasea.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(chckbxMostrarContrasea.isSelected()) {
                    passwordField.setEchoChar((char)0);
                } else {
                    passwordField.setEchoChar('*');
                }
            }
        });
        chckbxMostrarContrasea.setBounds(755, 340, 151, 21);
        chckbxMostrarContrasea.setFont(new Font("Monospaced", Font.PLAIN, 10));
        contentPane_1.add(chckbxMostrarContrasea);
    }
}
