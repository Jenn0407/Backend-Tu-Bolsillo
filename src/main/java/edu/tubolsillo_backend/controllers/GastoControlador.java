package edu.tubolsillo_backend.controllers;

import edu.tubolsillo_backend.models.Gasto;
import edu.tubolsillo_backend.services.GastoServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gastos")
public class GastoControlador {

    private final GastoServicio gastoServicio;

    public GastoControlador(GastoServicio gastoServicio) {
        this.gastoServicio = gastoServicio;
    }

    @GetMapping("/usuario/{userId}")
    public ResponseEntity<List<Gasto>> listarPorUsuario(@PathVariable Long userId, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } else {
            return ResponseEntity.ok(gastoServicio.obtenerPorUsuario(userId));
        }
    }

    @PostMapping
    public ResponseEntity<Gasto> crearGasto(@RequestBody Gasto gasto) {
        Gasto creado = gastoServicio.guardar(gasto);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        gastoServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
