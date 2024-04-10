package prTraveling;

public class Usuario {
    private String nombre;
    private String correoElectronico;
    private String contrasena;
    private String ubicacion;

    // Constructor
    public Usuario(String nombre, String correoElectronico, String contrasena, String ubicacion) {
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.contrasena = contrasena;
        this.ubicacion = ubicacion;
    }

    // M�todos getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrase�a(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    // M�todo para mostrar informaci�n del usuario
    public void mostrarInformacionUsuario() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Correo Electr�nico: " + correoElectronico);
        System.out.println("Ubicaci�n: " + ubicacion);
    }

}

