package edu.tubolsillo_backend.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String correo;

    @Column(nullable = false)
    private String contrasena;

    private LocalDateTime fechaIngreso;

    public Usuario(String nombre, String correo, String contrasena, LocalDateTime fechaIngreso) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.fechaIngreso = fechaIngreso;
    }

    public Usuario(Long id, String nombre, String correo, String contrasena, LocalDateTime fechaIngreso) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.fechaIngreso = fechaIngreso;
    }

    public Usuario() {
    }

    @PrePersist
    protected void prePersist() {
        this.fechaIngreso = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public LocalDateTime getFechaIngreso() {
        return fechaIngreso;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setFechaIngreso(LocalDateTime fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }


}
