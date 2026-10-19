package es.safareyes.sb_school_events1_nuevo.Repositorios;



import es.safareyes.sb_school_events1_nuevo.Modelos.Lugar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ILugar extends JpaRepository<Lugar, Integer> {
}