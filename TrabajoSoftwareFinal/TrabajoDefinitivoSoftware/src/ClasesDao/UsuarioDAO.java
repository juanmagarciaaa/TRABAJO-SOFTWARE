package ClasesDao;

import Clases.Reserva;
import Clases.Ubicacion;
import Clases.Usuario;
import Clases.Vehiculo;
import Clases.Viaje;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsuarioDAO {
	 private static Map<String, Usuario> usuarios = new HashMap<>();

    static {
        cargarUsuarios();
    }
    private static final String FILE_PATH = "usuarios.txt";

    public void addUsuario(Usuario usuario) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(usuario.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Usuario> getUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                Usuario usuario = new Usuario(fields[0], fields[1], fields[2], fields[3], Integer.parseInt(fields[4]), null, null, null, null, fields[5]);
                usuarios.add(usuario);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarios;
    }



    public static List<Usuario> cargarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] datos = line.split(",");
                if (datos.length >= 3) {
                    String email = datos[0];
                    String contrasena = datos[1];
                    String nombre = datos[2];
                    // Crea un nuevo usuario con los datos leídos del archivo
                    Usuario usuario = new Usuario(null, nombre, null, email, 0, null, null, null, null, contrasena);
                    usuarios.add(usuario);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarios;
    }


        public static Usuario buscarUsuario(String email, String contrasena) {
            List<Usuario> usuarios = cargarUsuarios();
            for (Usuario usuario : usuarios) {
                if (usuario.getEmail().equals(email) && usuario.getContrasena().equals(contrasena)) {
                    return usuario;
                }
            }
            return null;
        }

        private static Usuario stringToUsuario(String str) {
            String[] parts = str.split(",");
            if (parts.length < 9) return null;

            String DNI = parts[0];
            String nombre = parts[1];
            String apellido = parts[2];
            String email = parts[3];
            int telefono = Integer.parseInt(parts[4]);
            // Asumiendo que partes[5] y partes[6] contienen información de Vehiculo y Viaje compartidos
            // Convertir esto en listas vacías o implementar la lógica para cargar estas listas
            List<Vehiculo> vehiculos = new ArrayList<>();
            List<Viaje> viajesCompartidos = new ArrayList<>();
            List<Reserva> reservasRealizadas = new ArrayList<>();
            Ubicacion ubicacion = null; // Deberías implementar la conversión adecuada si es necesario
            String contrasena = parts[8];

            return new Usuario(DNI, nombre, apellido, email, telefono, vehiculos, viajesCompartidos, reservasRealizadas, ubicacion, contrasena);
        }
    

        public static Usuario obtenerUsuarioPorCorreo(String correo) {
            return usuarios.get(correo);
        }


        public static void actualizarUsuario(Usuario usuario) {
            List<Usuario> usuarios = cargarUsuarios();
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH,true))) {
                for (Usuario u : usuarios) {
                    if (u.getEmail().equals(usuario.getEmail())) {
                        bw.write(usuario.getDNI() + "," + usuario.getNombre() + "," + 
                                 usuario.getApellido() + "," + usuario.getEmail() + "," +
                                 usuario.getTelefono() + "," + usuario.getContrasena() + "\n");
                    } else {
                        bw.write(u.getDNI() + "," + u.getNombre() + "," + 
                                 u.getApellido() + "," + u.getEmail() + "," +
                                 u.getTelefono() + "," + u.getContrasena() + "\n");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    public static boolean esUsuarioNuevo(String correo) {
        Usuario usuario = obtenerUsuarioPorCorreo(correo);
        return usuario != null && (usuario.getNombre() == null || usuario.getApellido() == null || usuario.getTelefono() == 0);
    }
    public static void guardarUsuario(Usuario usuario) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            bw.write(usuario.getEmail() + "," + usuario.getContrasena() + "," + usuario.getNombre());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void eliminarUsuario(String email) {
        List<Usuario> usuarios = cargarUsuarios();
        boolean usuarioEliminado = false;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Usuario usuario : usuarios) {
                if (!usuario.getEmail().equals(email)) {
                    bw.write(usuario.getDNI() + "," + usuario.getNombre() + "," +
                            usuario.getApellido() + "," + usuario.getEmail() + "," +
                            usuario.getTelefono() + "," + usuario.getContrasena() + "\n");
                } else {
                    usuarioEliminado = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (usuarioEliminado) {
            System.out.println("Usuario eliminado con éxito.");
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }
}