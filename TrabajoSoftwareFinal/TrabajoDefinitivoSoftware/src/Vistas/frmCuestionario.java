package Vistas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import Clases.Usuario;
import ClasesDao.UsuarioDAO;
import Logica.UsuarioLogica;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class frmCuestionario extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtDNI;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtEmail;
    private JTextField txtTelefono;
    private JCheckBox chckbxVehiculo;
    private Usuario usuario;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frmCuestionario frame = new frmCuestionario(null);
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
    public frmCuestionario(Usuario usuario) {
        this.usuario = usuario;
        setTitle("Cuestionario de Usuario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 930, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setLocationRelativeTo(null);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel contentPane_1 = new JPanel();
        contentPane_1.setBounds(0, 0, 914, 561);
        contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane_1.setBackground(Color.WHITE);
        contentPane.add(contentPane_1);
        contentPane_1.setLayout(null);

        JLabel lblTitulo = new JLabel("Complete sus Datos");
        lblTitulo.setFont(new Font("Monospaced", Font.PLAIN, 25));
        lblTitulo.setBounds(330, 10, 260, 40);
        contentPane_1.add(lblTitulo);

        JLabel lblDNI = new JLabel("DNI:");
        lblDNI.setFont(new Font("Monospaced", Font.PLAIN, 20));
        lblDNI.setBounds(100, 80, 100, 30);
        contentPane_1.add(lblDNI);

        txtDNI = new JTextField();
        txtDNI.setFont(new Font("Monospaced", Font.PLAIN, 15));
        txtDNI.setBounds(250, 80, 200, 30);
        contentPane_1.add(txtDNI);
        txtDNI.setColumns(10);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Monospaced", Font.PLAIN, 20));
        lblNombre.setBounds(100, 130, 100, 30);
        contentPane_1.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setFont(new Font("Monospaced", Font.PLAIN, 15));
        txtNombre.setBounds(250, 130, 200, 30);
        contentPane_1.add(txtNombre);
        txtNombre.setColumns(10);

        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setFont(new Font("Monospaced", Font.PLAIN, 20));
        lblApellido.setBounds(100, 180, 100, 30);
        contentPane_1.add(lblApellido);

        txtApellido = new JTextField();
        txtApellido.setFont(new Font("Monospaced", Font.PLAIN, 15));
        txtApellido.setBounds(250, 180, 200, 30);
        contentPane_1.add(txtApellido);
        txtApellido.setColumns(10);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Monospaced", Font.PLAIN, 20));
        lblEmail.setBounds(100, 230, 100, 30);
        contentPane_1.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setFont(new Font("Monospaced", Font.PLAIN, 15));
        txtEmail.setBounds(250, 230, 200, 30);
        contentPane_1.add(txtEmail);
        txtEmail.setColumns(10);
        txtEmail.setText(usuario.getEmail());
        txtEmail.setEnabled(false);  // Email no se puede cambiar

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setFont(new Font("Monospaced", Font.PLAIN, 20));
        lblTelefono.setBounds(100, 280, 100, 30);
        contentPane_1.add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setFont(new Font("Monospaced", Font.PLAIN, 15));
        txtTelefono.setBounds(250, 280, 200, 30);
        contentPane_1.add(txtTelefono);
        txtTelefono.setColumns(10);

        JLabel lblVehiculo = new JLabel("¿Tiene vehículo?");
        lblVehiculo.setFont(new Font("Monospaced", Font.PLAIN, 20));
        lblVehiculo.setBounds(100, 330, 180, 30);
        contentPane_1.add(lblVehiculo);

        chckbxVehiculo = new JCheckBox("");
        chckbxVehiculo.setBounds(250, 330, 30, 30);
        contentPane_1.add(chckbxVehiculo);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setFont(new Font("Monospaced", Font.PLAIN, 15));
        btnGuardar.setBounds(250, 380, 120, 40);
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String DNI = txtDNI.getText();
                String nombre = txtNombre.getText();
                String apellido = txtApellido.getText();
                int telefono = Integer.parseInt(txtTelefono.getText());
                boolean tieneVehiculo = chckbxVehiculo.isSelected();

                // Actualizar los datos del usuario
                usuario.setDNI(DNI);
                usuario.setNombre(nombre);
                usuario.setApellido(apellido);
                usuario.setTelefono(telefono);

                if (tieneVehiculo) {
                    usuario.setVehiculos(new ArrayList<>());  // Agregar vehículos (lista vacía por ahora)
                }

                UsuarioDAO.actualizarUsuario(usuario);
                JOptionPane.showMessageDialog(null, "Datos actualizados con éxito.");
                
                // Regresar al formulario de inicio de sesión
                frmIniciarSesion inicioSesion = new frmIniciarSesion();
                inicioSesion.setVisible(true);
                dispose();
            }
        });
        contentPane_1.add(btnGuardar);
    }
}
