package edu.tubolsillo_backend.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Ingreso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private TipoDeIngreso tipo;

    private BigDecimal monto;

    private LocalDateTime fecha;

    public Ingreso(Usuario usuario, TipoDeIngreso tipo, BigDecimal monto) {
        this.usuario = usuario;
        this.tipo = tipo;
        this.monto = monto;
    }

    public Ingreso(Long id, Usuario usuario, TipoDeIngreso tipo, BigDecimal monto, LocalDateTime fecha) {
        this.id = id;
        this.usuario = usuario;
        this.tipo = tipo;
        this.monto = monto;
        this.fecha = fecha;
    }

    public Ingreso() {
    }

    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public TipoDeIngreso getTipo() {
        return tipo;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }
}
