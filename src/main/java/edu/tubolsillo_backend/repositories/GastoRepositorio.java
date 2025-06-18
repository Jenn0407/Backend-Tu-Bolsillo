package edu.tubolsillo_backend.repositories;

import edu.tubolsillo_backend.models.Gasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GastoRepositorio extends JpaRepository<Gasto, Long> {
    List<Gasto> findByUsuarioId(Long usuarioId);

}
