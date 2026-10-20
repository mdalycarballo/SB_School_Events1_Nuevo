package es.safareyes.sb_school_events1_nuevo.Repositorios;

import es.safareyes.sb_school_events1_nuevo.Modelos.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoria extends JpaRepository<Categoria, Integer> {

    List<Categoria> findAllByOrderByNombreAsc();

    List<Categoria> findByNombreContainingIgnoreCase(String nombre);
}