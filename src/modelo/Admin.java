package modelo;

public class Admin {
    private String usuario;
    private String contrasena;

    public Admin(String usuario, String contrasena){
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public boolean validarCredencial(String usuarioIngresado, String contrasenaIngresada){
        if (usuarioIngresado == null || contrasenaIngresada == null ){
            return false;
        }

        return usuario.equals(usuarioIngresado) && contrasena.equals(contrasenaIngresada);
        
    }
}

