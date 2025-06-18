package edu.tubolsillo_backend.controllers;

import edu.tubolsillo_backend.models.Ingreso;
import edu.tubolsillo_backend.services.IngresoServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingresos")
public class IngresoControlador {

    private final IngresoServicio ingresoServicio;

    public IngresoControlador(IngresoServicio ingresoServicio) {
        this.ingresoServicio = ingresoServicio;
    }

    @GetMapping("/usuario/{userId}")
    public ResponseEntity<List<Ingreso>> listarPorUsuario(@PathVariable Long userId, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } else {
            return ResponseEntity.ok(ingresoServicio.obtenerPorUsuarioId(userId));
        }
    }

    @PostMapping
    public ResponseEntity<Ingreso> crearIngreso(@RequestBody Ingreso ingreso,@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } else {
            Ingreso ingresoCreado = ingresoServicio.guardar(ingreso);
            return ResponseEntity.ok(ingresoCreado);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        ingresoServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

