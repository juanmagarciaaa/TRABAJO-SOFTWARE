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

    // Métodos getters y setters
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

    public void setContraseña(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    // Método para mostrar información del usuario
    public void mostrarInformacionUsuario() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Correo Electrónico: " + correoElectronico);
        System.out.println("Ubicación: " + ubicacion);
    }

}

