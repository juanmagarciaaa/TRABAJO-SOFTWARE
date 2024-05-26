package Vistas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Clases.Viaje;
import ClasesDao.ViajeDAO;

public class frmCrearViaje extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    public JTextField txtFecha;
    public JTextField txtOrigen;
    public JTextField txtDestino;
    public JTextField txtHora;
    public JTextField txtPlazasDisponibles;

    private ViajeDAO viajeDAO; // Instancia de ViajeDAO

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frmCrearViaje frame = new frmCrearViaje();
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
    public frmCrearViaje() {
        setResizable(false);
        setTitle("Traveling");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 930, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setLocationRelativeTo(null);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        viajeDAO = new ViajeDAO("viajes.txt"); // Inicialización de ViajeDAO con el archivo "viajes.txt"

        JLabel lblCrearViaje = new JLabel("Crear Viaje");
        lblCrearViaje.setFont(new Font("Monospaced", Font.PLAIN, 25));
        lblCrearViaje.setBounds(366, 10, 200, 30);
        contentPane.add(lblCrearViaje);

        JLabel lblFecha = new JLabel("Fecha (dd-MM):");
        lblFecha.setFont(new Font("Monospaced", Font.PLAIN, 15));
        lblFecha.setBounds(90, 181, 200, 30);
        contentPane.add(lblFecha);

        txtFecha = new JTextField();
        txtFecha.setFont(new Font("Monospaced", Font.PLAIN, 15));
        txtFecha.setBounds(300, 182, 446, 30);
        contentPane.add(txtFecha);
        txtFecha.setColumns(10);

        JLabel lblOrigen = new JLabel("Origen:");
        lblOrigen.setFont(new Font("Monospaced", Font.PLAIN, 15));
        lblOrigen.setBounds(100, 80, 200, 30);
        contentPane.add(lblOrigen);

        txtOrigen = new JTextField();
        txtOrigen.setFont(new Font("Monospaced", Font.PLAIN, 15));
        txtOrigen.setBounds(300, 81, 439, 30);
        contentPane.add(txtOrigen);
        txtOrigen.setColumns(10);

        JLabel lblDestino = new JLabel("Destino:");
        lblDestino.setFont(new Font("Monospaced", Font.PLAIN, 15));
        lblDestino.setBounds(100, 128, 200, 30);
        contentPane.add(lblDestino);

        txtDestino = new JTextField();
        txtDestino.setFont(new Font("Monospaced", Font.PLAIN, 15));
        txtDestino.setBounds(300, 129, 439, 30);
        contentPane.add(txtDestino);
        txtDestino.setColumns(10);

        JLabel lblHora = new JLabel("Hora (HH:mm):");
        lblHora.setFont(new Font("Monospaced", Font.PLAIN, 15));
        lblHora.setBounds(100, 230, 200, 30);
        contentPane.add(lblHora);

        txtHora = new JTextField();
        txtHora.setFont(new Font("Monospaced", Font.PLAIN, 15));
        txtHora.setBounds(300, 230, 446, 30);
        contentPane.add(txtHora);
        txtHora.setColumns(10);

        JLabel lblPlazasDisponibles = new JLabel("Plazas Disponibles:");
        lblPlazasDisponibles.setFont(new Font("Monospaced", Font.PLAIN, 15));
        lblPlazasDisponibles.setBounds(100, 280, 200, 30);
        contentPane.add(lblPlazasDisponibles);

        txtPlazasDisponibles = new JTextField();
        txtPlazasDisponibles.setFont(new Font("Monospaced", Font.PLAIN, 15));
        txtPlazasDisponibles.setBounds(300, 280, 446, 30);
        contentPane.add(txtPlazasDisponibles);
        txtPlazasDisponibles.setColumns(10);

        JButton btnCrearViaje = new JButton("Crear Viaje");
        btnCrearViaje.setFont(new Font("Monospaced", Font.PLAIN, 15));
        btnCrearViaje.setBounds(400, 417, 150, 40);
        btnCrearViaje.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String fecha = txtFecha.getText();
                    String origen = txtOrigen.getText();
                    String destino = txtDestino.getText();
                    String hora = txtHora.getText();
                    int plazasDisponibles = Integer.parseInt(txtPlazasDisponibles.getText());

                    Viaje nuevoViaje = new Viaje(fecha, origen, destino, hora, plazasDisponibles);
                    viajeDAO.guardarViaje(nuevoViaje);

                    JOptionPane.showMessageDialog(null, "Viaje creado exitosamente!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error en las plazas disponibles: " + ex.getMessage());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al crear el viaje: " + ex.getMessage());
                }
            }
        });
        contentPane.add(btnCrearViaje);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setFont(new Font("Monospaced", Font.PLAIN, 15));
        btnSalir.setBounds(587, 362, 200, 50);
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frmMenuPrincipal frmMenuPrincipal = new frmMenuPrincipal();
                frmMenuPrincipal.setVisible(true);
                dispose();
            }
        });
        contentPane.add(btnSalir);

        JLabel lblFoto = new JLabel("");
        lblFoto.setIcon(new ImageIcon(frmCrearViaje.class.getResource("/Componentes/fondoicono.jpg")));
        lblFoto.setBounds(0, 0, 1026, 573);
        contentPane.add(lblFoto);
    }
}
