package edu.tubolsillo_backend.controllers;

import edu.tubolsillo_backend.models.Usuario;
import edu.tubolsillo_backend.services.ServicioUsuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioControlador {
    private ServicioUsuario servicioUsuario;

    public UsuarioControlador(ServicioUsuario servicioUsuario) {
        this.servicioUsuario = servicioUsuario;
    }

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicioUsuario.guardar(usuario));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable long id) {
        return ResponseEntity.ok(servicioUsuario.obetenerUsuarioPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> eliminarUsuario(@PathVariable long id) {
        return ResponseEntity.ok(servicioUsuario.eliminarUsuarioPorId(id));
    }

}
