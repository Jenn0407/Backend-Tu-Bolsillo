package edu.tubolsillo_backend.controllers;

import edu.tubolsillo_backend.models.Usuario;
import edu.tubolsillo_backend.services.AutenticacionServicio;
import edu.tubolsillo_backend.utilities.Credenciales;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AutenticacionControlador {

    private final AutenticacionServicio autenticacionServicio;

    public AutenticacionControlador(AutenticacionServicio autenticacionServicio) {
        this.autenticacionServicio = autenticacionServicio;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registrar(@RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(autenticacionServicio.registrar(usuario));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> iniciarSesion(@RequestBody Credenciales credenciales) {
        Map<String, Object> respuesta = autenticacionServicio.autenticar(credenciales.getCorreo(), credenciales.getContrasena());
        return ResponseEntity.ok(respuesta);
    }
}
