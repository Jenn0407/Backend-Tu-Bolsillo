package edu.tubolsillo_backend.services;

import edu.tubolsillo_backend.models.Ingreso;
import edu.tubolsillo_backend.repositories.IngresoRepositorio;
import edu.tubolsillo_backend.utilities.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IngresoServicio {

    private final IngresoRepositorio ingresoRepositorio;

    public IngresoServicio(IngresoRepositorio ingresoRepositorio) {
        this.ingresoRepositorio = ingresoRepositorio;
    }

    @Transactional(readOnly = true)
    public List<Ingreso> obtenerPorUsuarioId(Long usuarioId) {
        return ingresoRepositorio.findByUsuarioId(usuarioId);
    }

    @Transactional(readOnly = true)
    public Ingreso obtenerPorId(Long id) {
        return ingresoRepositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ingreso no encontrado con id: " + id));
    }

    @Transactional(readOnly = true)
    public List<Ingreso> obtenerPorUsuario(Long usuarioId) {
        return ingresoRepositorio.findByUsuarioId(usuarioId);
    }

    @Transactional
    public Ingreso guardar(Ingreso ingreso) {
        return ingresoRepositorio.save(ingreso);
    }

    @Transactional
    public void eliminar(Long id) {
        if (!ingresoRepositorio.existsById(id)) {
            throw new ResourceNotFoundException("Ingreso no encontrado con id: " + id);
        }
        ingresoRepositorio.deleteById(id);
    }


}
