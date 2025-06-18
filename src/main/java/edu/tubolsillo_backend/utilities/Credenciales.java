package edu.tubolsillo_backend.utilities;

public class Credenciales {
    private final String correo;
    private final String contrasena;

    public Credenciales(String correo, String contrasena) {
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContrasena() {
        return contrasena;
    }
}
