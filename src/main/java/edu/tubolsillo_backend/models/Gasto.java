package edu.tubolsillo_backend.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Gasto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    private BigDecimal monto;

    private String descripcion;

    private LocalDateTime fecha;

    public Gasto(Usuario usuario, Categoria categoria, BigDecimal monto, String descripcion) {
        this.usuario = usuario;
        this.categoria = categoria;
        this.monto = monto;
        this.descripcion = descripcion;
    }

    public Gasto() {
    }

    @PrePersist
    protected void prePersist() {
        this.fecha = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }
}
