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

    ///////////////////Evento Prueba//

    @Autowired
    private IEvento eventoRepository;

    @Test
    @DisplayName("Prueba de conexion y guardado de Evento")
    void testGuardarYBuscarEvento() {
        // 1. Crear entidades dependientes para las claves foraneas
        Curso c = new Curso();
        c.nombre = "1º DAW " + System.currentTimeMillis();
        c.nivel = "FP Superior";
        c = cursoRepository.save(c);

        Usuario u = new Usuario();
        u.nombre = "Ana López";
        u.email = "ana_" + System.currentTimeMillis() + "@safareyes.es";
        u.password = "secret";
        u.tipo = "ORGANIZADOR";
        u.estado = "ACTIVO";
        u.fechaInscripcion = new Timestamp(System.currentTimeMillis());
        u.curso = c;
        u = usuarioRepository.save(u);

        Categoria cat = new Categoria();
        cat.nombre = "Ciencia " + System.currentTimeMillis();
        cat.icono = "atom.png";
        cat.color = "#FF5733";
        cat = categoriaRepository.save(cat);

        Lugar l = new Lugar();
        l.nombre = "Auditorio " + System.currentTimeMillis();
        l.aforoMax = 300;
        l = lugarRepository.save(l);

        // 2. Crear y guardar Evento
        Evento e = new Evento();
        e.titulo = "Feria de Ciencias " + System.currentTimeMillis();
        e.descripcion = "Presentacion de proyectos integradores";
        e.estado = "PUBLICADO";
        e.fechaInicio = new Timestamp(System.currentTimeMillis());
        e.fechaFin = new Timestamp(System.currentTimeMillis() + 3600000);
        e.aforoMaximo = 100;
        e.creador = u;
        e.categoria = cat;
        e.lugar = l;

        Evento eventoGuardado = eventoRepository.save(e);

        System.out.println("\n=======================================================");
        System.out.println("--> EVENTO RECUPERADO DE LA BD: " + eventoRepository.findById(eventoGuardado.id).orElse(null));
        System.out.println("=======================================================\n");
    }

    ///////////////////Evento Prueba//

    @Autowired
    private ICambio_evento cambioEventoRepository;

    @Test
    @DisplayName("Prueba de conexion y guardado de Cambio_evento")
    void testGuardarYBuscarCambioEvento() {
        // 1. Crear dependencias para Evento
        Curso c = new Curso();
        c.nombre = "2º DAW " + System.currentTimeMillis();
        c.nivel = "FP Superior";
        c = cursoRepository.save(c);

        Usuario u = new Usuario();
        u.nombre = "Pedro Picapiedra";
        u.email = "pedro_" + System.currentTimeMillis() + "@safareyes.es";
        u.password = "secret";
        u.tipo = "ORGANIZADOR";
        u.estado = "ACTIVO";
        u.fechaInscripcion = new Timestamp(System.currentTimeMillis());
        u.curso = c;
        u = usuarioRepository.save(u);

        Categoria cat = new Categoria();
        cat.nombre = "Deportes " + System.currentTimeMillis();
        cat.icono = "ball.png";
        cat.color = "#00FF00";
        cat = categoriaRepository.save(cat);

        Lugar l = new Lugar();
        l.nombre = "Gimnasio " + System.currentTimeMillis();
        l.aforoMax = 50;
        l = lugarRepository.save(l);

        Evento e = new Evento();
        e.titulo = "Torneo de Baloncesto " + System.currentTimeMillis();
        e.descripcion = "Competición interna";
        e.estado = "PUBLICADO";
        e.fechaInicio = new Timestamp(System.currentTimeMillis());
        e.fechaFin = new Timestamp(System.currentTimeMillis() + 7200000);
        e.aforoMaximo = 50;
        e.creador = u;
        e.categoria = cat;
        e.lugar = l;
        e = eventoRepository.save(e);

        // 2. Crear y guardar Cambio_evento
        Cambio_evento cambio = new Cambio_evento();
        cambio.evento = e;
        cambio.cambios = "Se modifica la hora de inicio a las 10:00";
        cambio.fecha = new Timestamp(System.currentTimeMillis());

        Cambio_evento cambioGuardado = cambioEventoRepository.save(cambio);

        System.out.println("\n=======================================================");
        System.out.println("--> CAMBIO EVENTO RECUPERADO DE LA BD: " + cambioEventoRepository.findById(cambioGuardado.id).orElse(null));
        System.out.println("=======================================================\n");
    }

    ///////////////////Evento Prueba//

    @Autowired
    private IEvento_curso eventoCursoRepository;

    @Test
    @DisplayName("Prueba de conexion y guardado de Evento_curso")
    void testGuardarYBuscarEventoCurso() {
        // 1. Crear dependencias para Evento y Curso
        Curso c = new Curso();
        c.nombre = "1º ASIR " + System.currentTimeMillis();
        c.nivel = "FP Superior";
        c = cursoRepository.save(c);

        Usuario u = new Usuario();
        u.nombre = "María García";
        u.email = "maria_" + System.currentTimeMillis() + "@safareyes.es";
        u.password = "secret";
        u.tipo = "ORGANIZADOR";
        u.estado = "ACTIVO";
        u.fechaInscripcion = new Timestamp(System.currentTimeMillis());
        u.curso = c;
        u = usuarioRepository.save(u);

        Categoria cat = new Categoria();
        cat.nombre = "Redes " + System.currentTimeMillis();
        cat.icono = "net.png";
        cat.color = "#FF00FF";
        cat = categoriaRepository.save(cat);

        Lugar l = new Lugar();
        l.nombre = "Aula Redes " + System.currentTimeMillis();
        l.aforoMax = 30;
        l = lugarRepository.save(l);

        Evento e = new Evento();
        e.titulo = "Taller de Ciberseguridad " + System.currentTimeMillis();
        e.descripcion = "Charla práctica de redes";
        e.estado = "PUBLICADO";
        e.fechaInicio = new Timestamp(System.currentTimeMillis());
        e.fechaFin = new Timestamp(System.currentTimeMillis() + 3600000);
        e.aforoMaximo = 30;
        e.creador = u;
        e.categoria = cat;
        e.lugar = l;
        e = eventoRepository.save(e);

        // 2. Asignar Evento a Curso mediante la entidad intermedia
        Evento_curso ec = new Evento_curso();
        ec.evento = e;
        ec.curso = c;

        Evento_curso eventoCursoGuardado = eventoCursoRepository.save(ec);

        System.out.println("\n=======================================================");
        System.out.println("--> EVENTO_CURSO RECUPERADO DE LA BD: " + eventoCursoRepository.findById(eventoCursoGuardado.id).orElse(null));
        System.out.println("=======================================================\n");
    }

    ///////////////////Evento_requisito Prueba//

    @Autowired
    private IEvento_requisito eventoRequisitoRepository;

    @Test
    @DisplayName("Prueba de conexion y guardado de Evento_requisito")
    void testGuardarYBuscarEventoRequisito() {
        // 1. Crear dependencias
        Curso c = new Curso();
        c.nombre = "2º ASIR " + System.currentTimeMillis();
        c.nivel = "FP Superior";
        c = cursoRepository.save(c);

        Usuario u = new Usuario();
        u.nombre = "Carlos Ruiz";
        u.email = "carlos_" + System.currentTimeMillis() + "@safareyes.es";
        u.password = "secret";
        u.tipo = "ORGANIZADOR";
        u.estado = "ACTIVO";
        u.fechaInscripcion = new Timestamp(System.currentTimeMillis());
        u.curso = c;
        u = usuarioRepository.save(u);

        Categoria cat = new Categoria();
        cat.nombre = "Sistemas " + System.currentTimeMillis();
        cat.icono = "sys.png";
        cat.color = "#00FFFF";
        cat = categoriaRepository.save(cat);

        Lugar l = new Lugar();
        l.nombre = "Laboratorio " + System.currentTimeMillis();
        l.aforoMax = 25;
        l = lugarRepository.save(l);

        Evento e = new Evento();
        e.titulo = "Hackathon " + System.currentTimeMillis();
        e.descripcion = "Maratón de desarrollo de software";
        e.estado = "PUBLICADO";
        e.fechaInicio = new Timestamp(System.currentTimeMillis());
        e.fechaFin = new Timestamp(System.currentTimeMillis() + 14400000);
        e.aforoMaximo = 25;
        e.creador = u;
        e.categoria = cat;
        e.lugar = l;
        e = eventoRepository.save(e);

        Requisitos r = new Requisitos();
        r.descripcion = "Traer regleta y ordenador " + System.currentTimeMillis();
        r = requisitosRepository.save(r);

        // 2. Crear y guardar Evento_requisito
        Evento_requisito er = new Evento_requisito();
        er.evento = e;
        er.requisito = r;

        Evento_requisito eventoRequisitoGuardado = eventoRequisitoRepository.save(er);

        System.out.println("\n=======================================================");
        System.out.println("--> EVENTO_REQUISITO RECUPERADO DE LA BD: " + eventoRequisitoRepository.findById(eventoRequisitoGuardado.id).orElse(null));
        System.out.println("=======================================================\n");
    }

    ///////////////////Inscripcion Prueba//

    @Autowired
    private IInscripcion inscripcionRepository;

    @Test
    @DisplayName("Prueba de conexion y guardado de Inscripcion")
    void testGuardarYBuscarInscripcion() {
        Curso c = new Curso();
        c.nombre = "1º SMR " + System.currentTimeMillis();
        c.nivel = "FP Medio";
        c = cursoRepository.save(c);

        Usuario alumno = new Usuario();
        alumno.nombre = "Lucía Blanco";
        alumno.email = "lucia_" + System.currentTimeMillis() + "@safareyes.es";
        alumno.password = "secret";
        alumno.tipo = "ESTUDIANTE";
        alumno.estado = "ACTIVO";
        alumno.fechaInscripcion = new Timestamp(System.currentTimeMillis());
        alumno.curso = c;
        alumno = usuarioRepository.save(alumno);

        Usuario organizador = new Usuario();
        organizador.nombre = "Profe Roberto";
        organizador.email = "roberto_" + System.currentTimeMillis() + "@safareyes.es";
        organizador.password = "secret";
        organizador.tipo = "ORGANIZADOR";
        organizador.estado = "ACTIVO";
        organizador.fechaInscripcion = new Timestamp(System.currentTimeMillis());
        organizador.curso = c;
        organizador = usuarioRepository.save(organizador);

        Categoria cat = new Categoria();
        cat.nombre = "Hardware " + System.currentTimeMillis();
        cat.icono = "hw.png";
        cat.color = "#FFFF00";
        cat = categoriaRepository.save(cat);

        Lugar l = new Lugar();
        l.nombre = "Taller Hardware " + System.currentTimeMillis();
        l.aforoMax = 20;
        l = lugarRepository.save(l);

        Evento e = new Evento();
        e.titulo = "Montaje de Servidores " + System.currentTimeMillis();
        e.descripcion = "Taller de ensamblado";
        e.estado = "PUBLICADO";
        e.fechaInicio = new Timestamp(System.currentTimeMillis());
        e.fechaFin = new Timestamp(System.currentTimeMillis() + 7200000);
        e.aforoMaximo = 20;
        e.creador = organizador;
        e.categoria = cat;
        e.lugar = l;
        e = eventoRepository.save(e);

        Inscripcion ins = new Inscripcion();
        ins.usuario = alumno;
        ins.evento = e;
        ins.fechaInscripcion = new Timestamp(System.currentTimeMillis());
        ins.tipo = "ASISTIRE";      // <-- Ajustado a los valores permitidos ("ASISTIRE" o "ME_INTERESA")
        ins.estado = "CONFIRMADA";  // <-- Ajustado a "CONFIRMADA"
        ins.asistio = false;

        Inscripcion inscripcionGuardada = inscripcionRepository.save(ins);

        System.out.println("\n=======================================================");
        System.out.println("--> INSCRIPCION RECUPERADA DE LA BD: " + inscripcionRepository.findById(inscripcionGuardada.id).orElse(null));
        System.out.println("=======================================================\n");
    }
    ///////////////////Evento Prueba//
}
