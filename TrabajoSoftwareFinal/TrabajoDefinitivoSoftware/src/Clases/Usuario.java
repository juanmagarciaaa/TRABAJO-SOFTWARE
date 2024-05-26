package Clases;

import java.util.List;

public class Usuario {
    private String DNI;
    private String nombre;
    private String apellido;
    private String email;
    private String contrasena;
    private int telefono;
    private List<Vehiculo> vehiculos;
    private List<Viaje> viajesCompartidos;
    private List<Reserva> reservasRealizadas;
    private Ubicacion ubicacion;
    
    public Usuario(String DNI,
                   String nombre, String apellido, String email,
                   int telefono, List<Vehiculo> vehiculos,
                   List<Viaje> viajesCompartidos,
                   List<Reserva> reservasRealizadas,
                   Ubicacion ubicacion, String contrasena) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.setTelefono(telefono);
        this.vehiculos = vehiculos;
        this.viajesCompartidos = viajesCompartidos;
        this.reservasRealizadas = reservasRealizadas;
        this.ubicacion = ubicacion;
        this.contrasena = contrasena;
    }

    public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public List<Vehiculo> getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(List<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}

	public List<Viaje> getViajesCompartidos() {
		return viajesCompartidos;
	}

	public void setViajesCompartidos(List<Viaje> viajesCompartidos) {
		this.viajesCompartidos = viajesCompartidos;
	}

	public List<Reserva> getReservasRealizadas() {
		return reservasRealizadas;
	}

	public void setReservasRealizadas(List<Reserva> reservasRealizadas) {
		this.reservasRealizadas = reservasRealizadas;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	

	 public String getDNI() { 
    	return DNI; 
    	}
    public void setDNI(String DNI) {
    	this.DNI = DNI; 
    	}

    @Override
    public String toString() {
        return email + "," + contrasena + "," + nombre;
    }

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
}
