package Logica;

import Clases.Usuario;
import ClasesDao.UsuarioDAO;

import java.util.List;

public class UsuarioLogica {
    private static List<Usuario> usuarios;

    public static void cargarUsuarios() {
        usuarios = UsuarioDAO.cargarUsuarios();
    }

    public static Usuario obtenerUsuarioPorEmail(String email) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email)) {
                return usuario;
            }
        }
        return null;
    }

    public static Usuario autentificar(String email, String contrasena) {
        Usuario usuario = obtenerUsuarioPorEmail(email);
        if (usuario != null && usuario.getContrasena().equals(contrasena)) {
            return usuario;
        }
        return null;
    }

    public static boolean esUsuarioNuevo(Usuario usuario) {
        return usuario.getDNI() == null && usuario.getNombre() == null && 
               usuario.getApellido() == null && usuario.getTelefono() == 0;
    }
}
