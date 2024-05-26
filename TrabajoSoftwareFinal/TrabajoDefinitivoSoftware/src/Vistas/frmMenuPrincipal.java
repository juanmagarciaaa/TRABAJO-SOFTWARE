package Vistas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

public class frmMenuPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frmMenuPrincipal frame = new frmMenuPrincipal();
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
    public frmMenuPrincipal() {
        setTitle("Menú Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 930, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setLocationRelativeTo(null);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblMenuPrincipal = new JLabel("Menú Principal");
        lblMenuPrincipal.setFont(new Font("Monospaced", Font.PLAIN, 25));
        lblMenuPrincipal.setBounds(572, 21, 224, 30);
        contentPane.add(lblMenuPrincipal);

        JButton btnCrearViaje = new JButton("Crear Viaje");
        btnCrearViaje.setFont(new Font("Monospaced", Font.PLAIN, 15));
        btnCrearViaje.setBounds(587, 113, 200, 50);
        btnCrearViaje.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frmCrearViaje crearViajeFrame = new frmCrearViaje();
                crearViajeFrame.setVisible(true);
                dispose(); // Cerrar el menú principal
            }
        });
        contentPane.add(btnCrearViaje);

        JButton btnBuscarViaje = new JButton("Buscar Viaje");
        btnBuscarViaje.setFont(new Font("Monospaced", Font.PLAIN, 15));
        btnBuscarViaje.setBounds(587, 236, 200, 50);
        btnBuscarViaje.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frmBuscarViaje buscarViajeFrame = new frmBuscarViaje();
                buscarViajeFrame.setVisible(true);
                dispose(); // Cerrar el menú principal
            }
        });
        contentPane.add(btnBuscarViaje);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setFont(new Font("Monospaced", Font.PLAIN, 15));
        btnSalir.setBounds(587, 362, 200, 50);
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frmIniciarSesion frmIniciarSesion = new frmIniciarSesion();
                frmIniciarSesion.setVisible(true);
                dispose();
                
            }
        });
        contentPane.add(btnSalir);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(frmMenuPrincipal.class.getResource("/Componentes/fondoicono.jpg")));
        lblNewLabel.setBounds(-271, 0, 759, 563);
        contentPane.add(lblNewLabel);
    }
}
