package es.safareyes.sb_school_events1_nuevo;

import es.safareyes.sb_school_events1_nuevo.Modelos.Categoria;
import es.safareyes.sb_school_events1_nuevo.Modelos.Curso;
import es.safareyes.sb_school_events1_nuevo.Repositorios.ICategoria;
import es.safareyes.sb_school_events1_nuevo.Repositorios.ICurso;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class Test_SE {

    ///////////////////Curso Prueba//

    @Autowired
    private ICurso cursoRepository;

    @Test
    @DisplayName("Prueba de conexion y guardado de Curso")
    void testGuardarYBuscarCurso() {
        // Creación e inserción usando directamente los campos públicos
        Curso c = new Curso();
        c.nombre = "1º DAM " + System.currentTimeMillis();
        c.nivel = "FP Superior";

        Curso cursoGuardado = cursoRepository.save(c);

        System.out.println("\n=======================================================");
        System.out.println("--> CURSO RECUPERADO DE LA BD: " + cursoRepository.findById(cursoGuardado.id).orElse(null));
        System.out.println("=======================================================\n");
    }

    ///////////////////Categoria Prueba//

    @Autowired
    private ICategoria categoriaRepository;

    @Test
    @DisplayName("Prueba de conexion y guardado de Categoria")
    void testGuardarYBuscarCategoria() {
        Categoria cat = new Categoria();
        cat.nombre = "Tecnología " + System.currentTimeMillis();
        cat.icono = "code.png";
        cat.color = "#3357FF";

        categoriaRepository.save(cat);

        List<Categoria> lista = categoriaRepository.findAllByOrderByNombreAsc();

        System.out.println("\n=======================================================");
        System.out.println("--> LISTADO CATEGORIAS RECUPERADAS: " + lista);
        System.out.println("=======================================================\n");
    }
}
