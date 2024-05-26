package Vistas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Clases.Viaje;
import ClasesDao.ViajeDAO;

public class frmBuscarViaje extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtOrigen;
    private JTextField txtDestino;
    private JTable table;
    private DefaultTableModel model;

    private ViajeDAO viajeDAO; // Instancia de ViajeDAO

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frmBuscarViaje frame = new frmBuscarViaje();
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
    public frmBuscarViaje() {
        setTitle("Traveling");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 930, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setLocationRelativeTo(null);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        viajeDAO = new ViajeDAO("viajes.txt"); // Inicialización de ViajeDAO con el archivo "viajes.txt"

        JLabel lblBuscarViaje = new JLabel("Buscar Viaje");
        lblBuscarViaje.setFont(new Font("Monospaced", Font.PLAIN, 25));
        lblBuscarViaje.setBounds(350, 10, 200, 30);
        contentPane.add(lblBuscarViaje);

        JLabel lblOrigen = new JLabel("Origen:");
        lblOrigen.setFont(new Font("Monospaced", Font.PLAIN, 15));
        lblOrigen.setBounds(100, 80, 200, 30);
        contentPane.add(lblOrigen);

        txtOrigen = new JTextField();
        txtOrigen.setFont(new Font("Monospaced", Font.PLAIN, 15));
        txtOrigen.setBounds(300, 80, 200, 30);
        contentPane.add(txtOrigen);
        txtOrigen.setColumns(10);

        JLabel lblDestino = new JLabel("Destino:");
        lblDestino.setFont(new Font("Monospaced", Font.PLAIN, 15));
        lblDestino.setBounds(100, 130, 200, 30);
        contentPane.add(lblDestino);

        txtDestino = new JTextField();
        txtDestino.setFont(new Font("Monospaced", Font.PLAIN, 15));
        txtDestino.setBounds(300, 130, 200, 30);
        contentPane.add(txtDestino);
        txtDestino.setColumns(10);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setFont(new Font("Monospaced", Font.PLAIN, 15));
        btnBuscar.setBounds(550, 105, 150, 40);
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarViajes(txtOrigen.getText(), txtDestino.getText());
            }
        });
        contentPane.add(btnBuscar);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(61, 172, 800, 300);
        contentPane.add(scrollPane);

        table = new JTable();
        model = new DefaultTableModel(
                new Object[][] {},
                new String[] {"Fecha", "Origen", "Destino", "Hora", "Plazas Disponibles"}
        );
        table.setModel(model);
        scrollPane.setViewportView(table);

        JButton btnApuntarse = new JButton("Apuntarse");
        btnApuntarse.setFont(new Font("Monospaced", Font.PLAIN, 15));
        btnApuntarse.setBounds(400, 515, 150, 40);
        btnApuntarse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                apuntarseAViaje();
            }
        });
        contentPane.add(btnApuntarse);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setFont(new Font("Monospaced", Font.PLAIN, 15));
        btnSalir.setBounds(684, 510, 178, 50);
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frmMenuPrincipal menu = new frmMenuPrincipal();
                menu.setVisible(true);
                dispose(); // Solo cierra la ventana actual
            }
        });
        contentPane.add(btnSalir);

        JLabel lblFoto = new JLabel("");
        lblFoto.setIcon(new ImageIcon(frmBuscarViaje.class.getResource("/Componentes/fondoicono.jpg")));
        lblFoto.setBounds(0, 0, 1026, 573);
        contentPane.add(lblFoto);
    }

    private void buscarViajes(String origen, String destino) {
        List<Viaje> viajes = viajeDAO.buscarViajes(origen, destino);
        model.setRowCount(0);  // Limpiar la tabla
        for (Viaje viaje : viajes) {
            model.addRow(new Object[]{
                    viaje.getFecha(),
                    viaje.getOrigen(),
                    viaje.getDestino(),
                    viaje.getHora(),
                    viaje.getPlazasDisponibles()
            });
        }
    }

    private void apuntarseAViaje() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            try {
                String fechaStr = (String) model.getValueAt(selectedRow, 0);
                String origen = (String) model.getValueAt(selectedRow, 1);
                String destino = (String) model.getValueAt(selectedRow, 2);
                String horaStr = (String) model.getValueAt(selectedRow, 3);
                int plazasDisponibles = (int) model.getValueAt(selectedRow, 4);

                Viaje viaje = new Viaje(fechaStr, origen, destino, horaStr, plazasDisponibles);
                boolean success = viajeDAO.apuntarseViaje(viaje);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Te has apuntado al viaje de " + origen + " a " + destino);
                    buscarViajes(txtOrigen.getText(), txtDestino.getText()); // Actualizar la tabla
                } else {
                    JOptionPane.showMessageDialog(this, "No hay plazas disponibles para este viaje.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al apuntarse al viaje.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un viaje.");
        }
    }
}
