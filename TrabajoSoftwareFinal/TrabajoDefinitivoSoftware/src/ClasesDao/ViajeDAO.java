package ClasesDao;

import Clases.Viaje;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViajeDAO {

    private String filePath; // Path del archivo de viajes

    public ViajeDAO(String filePath) {
        this.filePath = filePath;
    }

    public List<Viaje> cargarViajes() {
        List<Viaje> viajes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String fechaStr = parts[0].trim();
                    String origen = parts[1].trim();
                    String destino = parts[2].trim();
                    String horaStr = parts[3].trim();
                    int plazasDisponibles = Integer.parseInt(parts[4].trim());

                    Viaje viaje = new Viaje(fechaStr, origen, destino, horaStr, plazasDisponibles);
                    viajes.add(viaje);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return viajes;
    }

    public boolean guardarViaje(Viaje viaje) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            String line = String.format("%s,%s,%s,%s,%d%n", viaje.getFecha(), viaje.getOrigen(), viaje.getDestino(),
                    viaje.getHora(), viaje.getPlazasDisponibles());
            bw.write(line);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Viaje> buscarViajes(String origen, String destino) {
        List<Viaje> viajes = new ArrayList<>();
        List<Viaje> todosLosViajes = cargarViajes();

        for (Viaje viaje : todosLosViajes) {
            if (viaje.getOrigen().equalsIgnoreCase(origen) && viaje.getDestino().equalsIgnoreCase(destino)) {
                viajes.add(viaje);
            }
        }

        return viajes;
    }

    public boolean apuntarseViaje(Viaje viaje) {
        List<Viaje> todosLosViajes = cargarViajes();

        for (Viaje v : todosLosViajes) {
            if (v.getFecha().equals(viaje.getFecha()) && v.getOrigen().equalsIgnoreCase(viaje.getOrigen())
                    && v.getDestino().equalsIgnoreCase(viaje.getDestino()) && v.getHora().equals(viaje.getHora())) {
                if (v.getPlazasDisponibles() > 0) {
                    v.setPlazasDisponibles(v.getPlazasDisponibles() - 1);
                    actualizarViajes(todosLosViajes);
                    return true;
                }
            }
        }
        return false;
    }

    public void actualizarViajes(List<Viaje> viajes) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Viaje viaje : viajes) {
                String line = String.format("%s,%s,%s,%s,%d%n", viaje.getFecha(), viaje.getOrigen(), viaje.getDestino(),
                        viaje.getHora(), viaje.getPlazasDisponibles());
                bw.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
