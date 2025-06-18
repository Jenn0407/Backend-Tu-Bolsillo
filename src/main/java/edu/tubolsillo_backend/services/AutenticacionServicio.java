package edu.tubolsillo_backend.services;

import edu.tubolsillo_backend.models.Usuario;
import edu.tubolsillo_backend.repositories.UsuarioRepositorio;
import edu.tubolsillo_backend.utilities.JWTUtil;
import edu.tubolsillo_backend.utilities.ResourceNotFoundException;
import edu.tubolsillo_backend.utilities.UsuarioDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AutenticacionServicio {

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final UsuarioRepositorio usuarioRepositorio;

    private final JWTUtil jwtUtil;

    public AutenticacionServicio(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, UsuarioRepositorio usuarioRepositorio, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepositorio = usuarioRepositorio;
        this.jwtUtil = jwtUtil;
    }

    public String registrar(Usuario usuario) {
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        usuarioRepositorio.save(usuario);
        return jwtUtil.generarToken(usuario);
    }

    public Map<String, Object> autenticar(String correo, String contrasena) {

        Usuario usuario = usuarioRepositorio.findByCorreo(correo)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(correo, contrasena));

        String token = jwtUtil.generarToken(usuario);
        UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("token", token);
        respuesta.put("user", usuarioDTO);

        return respuesta;
    }
}
