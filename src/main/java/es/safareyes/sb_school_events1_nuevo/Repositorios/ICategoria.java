package es.safareyes.sb_school_events1_nuevo.Repositorios;

import es.safareyes.sb_school_events1_nuevo.Modelos.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICategoria extends JpaRepository<Categoria, Integer> {
    List<Categoria> findAllByOrderByNombreAsc();
}