package edu.tubolsillo_backend.services;

import edu.tubolsillo_backend.models.Usuario;
import edu.tubolsillo_backend.repositories.UsuarioRepositorio;
import edu.tubolsillo_backend.utilities.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServicioUsuario implements UserDetailsService {

    private final UsuarioRepositorio usuarioRepositorio;

    public ServicioUsuario(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario user = usuarioRepositorio.findByCorreo(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(
                user.getCorreo(),
                user.getContrasena(),
                List.of());
    }

    @Transactional
    public Usuario guardar(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }

    public Usuario obetenerUsuarioPorId(long id) {
        return usuarioRepositorio.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Transactional
    public Usuario eliminarUsuarioPorId(long id) {
        Usuario usuarioDb = usuarioRepositorio.findById(id).orElseThrow(IllegalArgumentException::new);
        usuarioRepositorio.deleteById(id);
        return usuarioDb;
    }
}
