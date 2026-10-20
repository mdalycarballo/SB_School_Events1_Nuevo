package es.safareyes.sb_school_events1_nuevo.Repositorios;

import es.safareyes.sb_school_events1_nuevo.Modelos.Usuario;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUsuario extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByEmail(String email);

    @EntityGraph(attributePaths = {"curso"})
    List<Usuario> findByTipo(String tipo);

    @EntityGraph(attributePaths = {"curso"})
    List<Usuario> findByEstado(String estado);

    @EntityGraph(attributePaths = {"curso"})
    List<Usuario> findByCurso_Id(Integer cursoId);

    @EntityGraph(attributePaths = {"curso"})
    List<Usuario> findByNombreContainingIgnoreCase(String nombre);
}
