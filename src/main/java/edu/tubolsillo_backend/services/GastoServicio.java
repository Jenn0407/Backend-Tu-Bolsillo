package edu.tubolsillo_backend.services;

import edu.tubolsillo_backend.models.Gasto;
import edu.tubolsillo_backend.repositories.GastoRepositorio;
import edu.tubolsillo_backend.utilities.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GastoServicio {

    private final GastoRepositorio gastoRepositorio;

    public GastoServicio(GastoRepositorio gastoRepositorio) {
        this.gastoRepositorio = gastoRepositorio;
    }

    @Transactional(readOnly = true)
    public List<Gasto> obtenerTodos() {
        return gastoRepositorio.findAll();
    }

    @Transactional(readOnly = true)
    public Gasto obtenerPorId(Long id) {
        return gastoRepositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gasto no encontrado con id: " + id));
    }

    @Transactional(readOnly = true)
    public List<Gasto> obtenerPorUsuario(Long usuarioId) {
        return gastoRepositorio.findByUsuarioId(usuarioId);
    }

    @Transactional
    public Gasto guardar(Gasto gasto) {
        return gastoRepositorio.save(gasto);
    }

    @Transactional
    public void eliminar(Long id) {
        if (!gastoRepositorio.existsById(id)) {
            throw new ResourceNotFoundException("Gasto no encontrado con id: " + id);
        }
        gastoRepositorio.deleteById(id);
    }
}

