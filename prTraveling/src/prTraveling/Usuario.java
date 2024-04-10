package prTraveling;

public class Usuario {
    private String nombre;
    private String correoElectronico;
    private String contraseña;
    private String ubicacion;

    // Constructor
    public Usuario(String nombre, String correoElectronico, String contraseña, String ubicacion) {
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.contraseña = contraseña;
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

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
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

