package es.safareyes.sb_school_events1_nuevo.repositorios;



import es.safareyes.sb_school_events1_nuevo.modelos.Lugar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILugar extends JpaRepository<Lugar, Integer> {

    List<Lugar> findByAforoMaxGreaterThanEqual(Integer aforoMinimo);

    List<Lugar> findByNombreContainingIgnoreCase(String nombre);
}