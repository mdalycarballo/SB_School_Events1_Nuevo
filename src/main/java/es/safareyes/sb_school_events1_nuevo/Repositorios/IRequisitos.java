package es.safareyes.sb_school_events1_nuevo.Repositorios;

import es.safareyes.sb_school_events1_nuevo.Modelos.Requisitos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRequisitos extends JpaRepository<Requisitos, Integer> {

    List<Requisitos> findByDescripcionContainingIgnoreCase(String descripcion);
}