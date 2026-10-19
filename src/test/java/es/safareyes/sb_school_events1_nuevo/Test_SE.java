package es.safareyes.sb_school_events1_nuevo;

import es.safareyes.sb_school_events1_nuevo.Modelos.*;
import es.safareyes.sb_school_events1_nuevo.Repositorios.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
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

    ///////////////////Lugar Prueba//

    @Autowired
    private ILugar lugarRepository;

    @Test
    @DisplayName("Prueba de conexion y guardado de Lugar")
    void testGuardarYBuscarLugar() {
        Lugar l = new Lugar();
        l.nombre = "Salón de Actos " + System.currentTimeMillis();
        l.aforoMax = 150;

        Lugar lugarGuardado = lugarRepository.save(l);

        System.out.println("\n=======================================================");
        System.out.println("--> LUGAR RECUPERADO DE LA BD: " + lugarRepository.findById(lugarGuardado.id).orElse(null));
        System.out.println("=======================================================\n");
    }

    ///////////////////Lugar Prueba//

    @Autowired
    private IRequisitos requisitosRepository;

    @Test
    @DisplayName("Prueba de conexion y guardado de Requisitos")
    void testGuardarYBuscarRequisitos() {
        Requisitos r = new Requisitos();
        r.descripcion = "Llevar portátil " + System.currentTimeMillis();

        Requisitos requisitoGuardado = requisitosRepository.save(r);

        System.out.println("\n=======================================================");
        System.out.println("--> REQUISITO RECUPERADO DE LA BD: " + requisitosRepository.findById(requisitoGuardado.id).orElse(null));
        System.out.println("=======================================================\n");
    }

    ///////////////////Lugar Prueba//

    @Autowired
    private IUsuario usuarioRepository;

    @Test
    @DisplayName("Prueba de conexion y guardado de Usuario")
    void testGuardarYBuscarUsuario() {
        // Creamos y guardamos un curso previo para la relacion ManyToOne
        Curso c = new Curso();
        c.nombre = "2º DAM " + System.currentTimeMillis();
        c.nivel = "FP Superior";
        c = cursoRepository.save(c);

        Usuario u = new Usuario();
        u.nombre = "Juan Pérez";
        u.email = "juan_" + System.currentTimeMillis() + "@safareyes.es";
        u.password = "secret";
        u.tipo = "ESTUDIANTE"; // <-- Valor exacto en mayúsculas según la base de datos
        u.estado = "ACTIVO";
        u.fechaInscripcion = new Timestamp(System.currentTimeMillis());
        u.curso = c;

        Usuario usuarioGuardado = usuarioRepository.save(u);

        System.out.println("\n=======================================================");
        System.out.println("--> USUARIO RECUPERADO DE LA BD: " + usuarioRepository.findById(usuarioGuardado.id).orElse(null));
        System.out.println("=======================================================\n");
    }

}
