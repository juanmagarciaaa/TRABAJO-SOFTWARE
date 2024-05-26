package Clases;

public class Viaje {
    private String fecha;
    private String origen;
    private String destino;
    private String hora;
    private int plazasDisponibles;

    public Viaje(String fecha, String origen, String destino, String hora, int plazasDisponibles) {
        this.fecha = fecha;
        this.origen = origen;
        this.destino = destino;
        this.hora = hora;
        this.plazasDisponibles = plazasDisponibles;
    }

    // Getters y setters (métodos de acceso)
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getPlazasDisponibles() {
        return plazasDisponibles;
    }

    public void setPlazasDisponibles(int plazasDisponibles) {
        this.plazasDisponibles = plazasDisponibles;
    }
}
