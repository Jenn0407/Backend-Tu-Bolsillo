package edu.tubolsillo_backend.utilities;

import edu.tubolsillo_backend.models.Usuario;

import java.time.LocalDateTime;

public class UsuarioDTO {
    private final Long id;
    private final String nombre;
    private final String correo;
    private final LocalDateTime fechaIngreso;

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nombre = usuario.getNombre();
        this.correo = usuario.getCorreo();
        this.fechaIngreso = usuario.getFechaIngreso();
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getFechaIngreso() {
        return fechaIngreso;
    }
}
