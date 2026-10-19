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

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@Transactional
public class Test_SE {
//---------------------------------------------pruebas para cada interfaz---------//


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
    ///////////////////Notificacion Prueba//

    @Autowired
    private INotificacion notificacionRepository;

    @Test
    @DisplayName("Prueba de conexion y guardado de Notificacion")
    void testGuardarYBuscarNotificacion() {
        Curso c = new Curso();
        c.nombre = "2º SMR " + System.currentTimeMillis();
        c.nivel = "FP Medio";
        c = cursoRepository.save(c);

        Usuario destinatario = new Usuario();
        destinatario.nombre = "Sonia Molina";
        destinatario.email = "sonia_" + System.currentTimeMillis() + "@safareyes.es";
        destinatario.password = "secret";
        destinatario.tipo = "ESTUDIANTE";
        destinatario.estado = "ACTIVO";
        destinatario.fechaInscripcion = new Timestamp(System.currentTimeMillis());
        destinatario.curso = c;
        destinatario = usuarioRepository.save(destinatario);

        Categoria cat = new Categoria();
        cat.nombre = "Aviso " + System.currentTimeMillis();
        cat.icono = "bell.png";
        cat.color = "#FF0000";
        cat = categoriaRepository.save(cat);

        Lugar l = new Lugar();
        l.nombre = "Aula Magna " + System.currentTimeMillis();
        l.aforoMax = 200;
        l = lugarRepository.save(l);

        Evento e = new Evento();
        e.titulo = "Reunión de Orientación " + System.currentTimeMillis();
        e.descripcion = "Sesión informativa";
        e.estado = "PUBLICADO";
        e.fechaInicio = new Timestamp(System.currentTimeMillis());
        e.fechaFin = new Timestamp(System.currentTimeMillis() + 3600000);
        e.aforoMaximo = 200;
        e.creador = destinatario;
        e.categoria = cat;
        e.lugar = l;
        e = eventoRepository.save(e);

        Notificacion notif = new Notificacion();
        notif.usuario = destinatario;
        notif.evento = e;
        notif.tipo = "RECORDATORIO_24H"; // <-- Ajustado al valor exacto admitido por el CHECK de la BD
        notif.mensaje = "Recordatorio: El evento dará comienzo en 24 horas.";
        notif.fecha = new Timestamp(System.currentTimeMillis());
        notif.leida = false;

        Notificacion notificacionGuardada = notificacionRepository.save(notif);

        System.out.println("\n=======================================================");
        System.out.println("--> NOTIFICACION RECUPERADA DE LA BD: " + notificacionRepository.findById(notificacionGuardada.id).orElse(null));
        System.out.println("=======================================================\n");
    }

    ///////////////////Publicacion Prueba//

    @Autowired
    private IPublicacion publicacionRepository;

    @Test
    @DisplayName("Prueba de conexion y guardado de Publicacion")
    void testGuardarYBuscarPublicacion() {
        // 1. Crear dependencias para Usuario y Evento
        Curso c = new Curso();
        c.nombre = "1º Comercio " + System.currentTimeMillis();
        c.nivel = "FP Medio";
        c = cursoRepository.save(c);

        Usuario autor = new Usuario();
        autor.nombre = "Manuel Ramos";
        autor.email = "manuel_" + System.currentTimeMillis() + "@safareyes.es";
        autor.password = "secret";
        autor.tipo = "ORGANIZADOR";
        autor.estado = "ACTIVO";
        autor.fechaInscripcion = new Timestamp(System.currentTimeMillis());
        autor.curso = c;
        autor = usuarioRepository.save(autor);

        Categoria cat = new Categoria();
        cat.nombre = "Marketing " + System.currentTimeMillis();
        cat.icono = "mkt.png";
        cat.color = "#0000FF";
        cat = categoriaRepository.save(cat);

        Lugar l = new Lugar();
        l.nombre = "Sala de Reuniones " + System.currentTimeMillis();
        l.aforoMax = 40;
        l = lugarRepository.save(l);

        Evento e = new Evento();
        e.titulo = "Charla de Emprendimiento " + System.currentTimeMillis();
        e.descripcion = "Estrategias de ventas digitales";
        e.estado = "PUBLICADO";
        e.fechaInicio = new Timestamp(System.currentTimeMillis());
        e.fechaFin = new Timestamp(System.currentTimeMillis() + 5400000);
        e.aforoMaximo = 40;
        e.creador = autor;
        e.categoria = cat;
        e.lugar = l;
        e = eventoRepository.save(e);

        // 2. Crear y guardar Publicacion
        Publicacion pub = new Publicacion();
        pub.evento = e;
        pub.usuario = autor;
        pub.texto = "¡Ya están disponibles las diapositivas de la presentación!";
        pub.fecha = new Timestamp(System.currentTimeMillis());

        Publicacion publicacionGuardada = publicacionRepository.save(pub);

        System.out.println("\n=======================================================");
        System.out.println("--> PUBLICACION RECUPERADA DE LA BD: " + publicacionRepository.findById(publicacionGuardada.id).orElse(null));
        System.out.println("=======================================================\n");
    }

    ///////////////////Foto_publicacion Prueba//

    @Autowired
    private IFoto_publicacion fotoPublicacionRepository;

    @Test
    @DisplayName("Prueba de conexion y guardado de Foto_publicacion")
    void testGuardarYBuscarFotoPublicacion() {
        // 1. Crear dependencias para la Publicación
        Curso c = new Curso();
        c.nombre = "2º Comercio " + System.currentTimeMillis();
        c.nivel = "FP Medio";
        c = cursoRepository.save(c);

        Usuario autor = new Usuario();
        autor.nombre = "Laura Serrano";
        autor.email = "laura_" + System.currentTimeMillis() + "@safareyes.es";
        autor.password = "secret";
        autor.tipo = "ORGANIZADOR";
        autor.estado = "ACTIVO";
        autor.fechaInscripcion = new Timestamp(System.currentTimeMillis());
        autor.curso = c;
        autor = usuarioRepository.save(autor);

        Categoria cat = new Categoria();
        cat.nombre = "Fotografía " + System.currentTimeMillis();
        cat.icono = "cam.png";
        cat.color = "#800080";
        cat = categoriaRepository.save(cat);

        Lugar l = new Lugar();
        l.nombre = "Plaza Central " + System.currentTimeMillis();
        l.aforoMax = 500;
        l = lugarRepository.save(l);

        Evento e = new Evento();
        e.titulo = "Exposición Fotográfica " + System.currentTimeMillis();
        e.descripcion = "Muestra de arte de alumnos";
        e.estado = "PUBLICADO";
        e.fechaInicio = new Timestamp(System.currentTimeMillis());
        e.fechaFin = new Timestamp(System.currentTimeMillis() + 10800000);
        e.aforoMaximo = 500;
        e.creador = autor;
        e.categoria = cat;
        e.lugar = l;
        e = eventoRepository.save(e);

        Publicacion pub = new Publicacion();
        pub.evento = e;
        pub.usuario = autor;
        pub.texto = "Resumen gráfico del evento de inauguración.";
        pub.fecha = new Timestamp(System.currentTimeMillis());
        pub = publicacionRepository.save(pub);

        // 2. Crear y guardar Foto_publicacion
        Foto_publicacion foto = new Foto_publicacion();
        foto.publicacion = pub;
        foto.urlFoto = "https://midominio.es/uploads/foto_" + System.currentTimeMillis() + ".jpg";

        Foto_publicacion fotoGuardada = fotoPublicacionRepository.save(foto);

        System.out.println("\n=======================================================");
        System.out.println("--> FOTO_PUBLICACION RECUPERADA DE LA BD: " + fotoPublicacionRepository.findById(fotoGuardada.id).orElse(null));
        System.out.println("=======================================================\n");
    }

    //-------------------------TEST GENERAL---------------------//

    @Test
    @DisplayName("Prueba General: Flujo completo e integración de las 13 entidades")
    void testPruebaGeneralModelosYConsultas() {
        System.out.println("\n=======================================================");
        System.out.println(">>> INICIANDO TEST GENERAL DE INTEGRACIÓN DE 13 ENTIDADES <<<");
        System.out.println("=======================================================\n");

        long ts = System.currentTimeMillis();

        // 1. Curso
        Curso curso = new Curso();
        curso.nombre = "Curso General " + ts;
        curso.nivel = "FP Superior";
        curso = cursoRepository.save(curso);

        // 2. Categoria
        Categoria categoria = new Categoria();
        categoria.nombre = "Categoría General " + ts;
        categoria.icono = "icon_general.png";
        categoria.color = "#00FF88";
        categoria = categoriaRepository.save(categoria);

        // 3. Lugar
        Lugar lugar = new Lugar();
        lugar.nombre = "Lugar General " + ts;
        lugar.aforoMax = 250;
        lugar = lugarRepository.save(lugar);

        // 4. Requisitos
        Requisitos requisito = new Requisitos();
        requisito.descripcion = "Requisito General " + ts;
        requisito = requisitosRepository.save(requisito);

        // 5. Usuario
        Usuario usuario = new Usuario();
        usuario.nombre = "Usuario Test " + ts;
        usuario.email = "test_" + ts + "@safareyes.es";
        usuario.password = "pass123";
        usuario.tipo = "ORGANIZADOR";
        usuario.estado = "ACTIVO";
        usuario.fechaInscripcion = new Timestamp(ts);
        usuario.curso = curso;
        usuario = usuarioRepository.save(usuario);

        // 6. Evento
        Evento evento = new Evento();
        evento.titulo = "Evento Magna Integración " + ts;
        evento.descripcion = "Demostración de persistencia completa";
        evento.estado = "PUBLICADO";
        evento.fechaInicio = new Timestamp(ts);
        evento.fechaFin = new Timestamp(ts + 7200000);
        evento.aforoMaximo = 150;
        evento.creador = usuario;
        evento.categoria = categoria;
        evento.lugar = lugar;
        evento = eventoRepository.save(evento);

        // 7. Cambio_evento
        Cambio_evento cambio = new Cambio_evento();
        cambio.evento = evento;
        cambio.cambios = "Cambio de prueba en el evento general";
        cambio.fecha = new Timestamp(ts);
        cambio = cambioEventoRepository.save(cambio);

        // 8. Evento_curso
        Evento_curso eventoCurso = new Evento_curso();
        eventoCurso.evento = evento;
        eventoCurso.curso = curso;
        eventoCurso = eventoCursoRepository.save(eventoCurso);

        // 9. Evento_requisito
        Evento_requisito eventoRequisito = new Evento_requisito();
        eventoRequisito.evento = evento;
        eventoRequisito.requisito = requisito;
        eventoRequisito = eventoRequisitoRepository.save(eventoRequisito);

        // 10. Inscripcion
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.usuario = usuario;
        inscripcion.evento = evento;
        inscripcion.fechaInscripcion = new Timestamp(ts);
        inscripcion.tipo = "ASISTIRE";
        inscripcion.estado = "CONFIRMADA";
        inscripcion.asistio = true;
        inscripcion = inscripcionRepository.save(inscripcion);

        // 11. Notificacion
        Notificacion notificacion = new Notificacion();
        notificacion.usuario = usuario;
        notificacion.evento = evento;
        notificacion.tipo = "RECORDATORIO_24H";
        notificacion.mensaje = "Aviso general generado por el test";
        notificacion.fecha = new Timestamp(ts);
        notificacion.leida = false;
        notificacion = notificacionRepository.save(notificacion);

        // 12. Publicacion
        Publicacion publicacion = new Publicacion();
        publicacion.evento = evento;
        publicacion.usuario = usuario;
        publicacion.texto = "Publicación general del evento de prueba";
        publicacion.fecha = new Timestamp(ts);
        publicacion = publicacionRepository.save(publicacion);

        // 13. Foto_publicacion
        Foto_publicacion foto = new Foto_publicacion();
        foto.publicacion = publicacion;
        foto.urlFoto = "https://midominio.es/uploads/general_" + ts + ".png";
        foto = fotoPublicacionRepository.save(foto);

        // IMPRESIÓN Y RECUPERACIÓN DE TODAS LAS ENTIDADES DESDE LA BD
        System.out.println("1.  Curso:            " + cursoRepository.findById(curso.id).orElse(null));
        System.out.println("2.  Categoría:        " + categoriaRepository.findById(categoria.id).orElse(null));
        System.out.println("3.  Lugar:            " + lugarRepository.findById(lugar.id).orElse(null));
        System.out.println("4.  Requisito:        " + requisitosRepository.findById(requisito.id).orElse(null));
        System.out.println("5.  Usuario:          " + usuarioRepository.findById(usuario.id).orElse(null));
        System.out.println("6.  Evento:           " + eventoRepository.findById(evento.id).orElse(null));
        System.out.println("7.  Cambio Evento:    " + cambioEventoRepository.findById(cambio.id).orElse(null));
        System.out.println("8.  Evento Curso:     " + eventoCursoRepository.findById(eventoCurso.id).orElse(null));
        System.out.println("9.  Evento Requisito: " + eventoRequisitoRepository.findById(eventoRequisito.id).orElse(null));
        System.out.println("10. Inscripción:      " + inscripcionRepository.findById(inscripcion.id).orElse(null));
        System.out.println("11. Notificación:     " + notificacionRepository.findById(notificacion.id).orElse(null));
        System.out.println("12. Publicación:      " + publicacionRepository.findById(publicacion.id).orElse(null));
        System.out.println("13. Foto Publicación: " + fotoPublicacionRepository.findById(foto.id).orElse(null));

        System.out.println("\n=======================================================");
        System.out.println(">>> PRUEBA GENERAL COMPLETADA CON ÉXITO <<<");
        System.out.println("=======================================================\n");
    }

    //-------------------TEST INDIVIDUALES-----------------//

    @Autowired private ICurso cursoRepository;
    @Autowired private ICategoria categoriaRepository;
    @Autowired private ILugar lugarRepository;
    @Autowired private IRequisitos requisitosRepository;
    @Autowired private IUsuario usuarioRepository;
    @Autowired private IEvento eventoRepository;
    @Autowired private ICambio_evento cambioEventoRepository;
    @Autowired private IEvento_curso eventoCursoRepository;
    @Autowired private IEvento_requisito eventoRequisitoRepository;
    @Autowired private IInscripcion inscripcionRepository;
    @Autowired private INotificacion notificacionRepository;
    @Autowired private IPublicacion publicacionRepository;
    @Autowired private IFoto_publicacion fotoPublicacionRepository;

    @Test
    @DisplayName("Pruebas de consultas en ICurso")
    void testConsultasCurso() {
        Curso c = new Curso();
        c.nombre = "Curso Especial DAM";
        c.nivel = "FP Superior";
        cursoRepository.save(c);

        List<Curso> porNivel = cursoRepository.findByNivel("FP Superior");
        List<Curso> porNombre = cursoRepository.findByNombreContainingIgnoreCase("Especial");

        assertFalse(porNivel.isEmpty());
        assertFalse(porNombre.isEmpty());
    }

    @Test
    @DisplayName("Pruebas de consultas en ICategoria")
    void testConsultasCategoria() {
        Categoria cat = new Categoria();
        cat.nombre = "Robótica";
        cat.icono = "bot.png";
        cat.color = "#FF0000";
        categoriaRepository.save(cat);

        List<Categoria> ordenadas = categoriaRepository.findAllByOrderByNombreAsc();
        List<Categoria> porNombre = categoriaRepository.findByNombreContainingIgnoreCase("Robót");

        assertFalse(ordenadas.isEmpty());
        assertFalse(porNombre.isEmpty());
    }

    @Test
    @DisplayName("Pruebas de consultas en ILugar")
    void testConsultasLugar() {
        Lugar l = new Lugar();
        l.nombre = "Pabellón Cubierto";
        l.aforoMax = 500;
        lugarRepository.save(l);

        List<Lugar> porAforo = lugarRepository.findByAforoMaxGreaterThanEqual(300);
        List<Lugar> porNombre = lugarRepository.findByNombreContainingIgnoreCase("Pabellón");

        assertFalse(porAforo.isEmpty());
        assertFalse(porNombre.isEmpty());
    }

    @Test
    @DisplayName("Pruebas de consultas en IRequisitos")
    void testConsultasRequisitos() {
        Requisitos r = new Requisitos();
        r.descripcion = "Autorización firmada por tutor legal";
        requisitosRepository.save(r);

        List<Requisitos> porDesc = requisitosRepository.findByDescripcionContainingIgnoreCase("firmada");
        assertFalse(porDesc.isEmpty());
    }

    @Test
    @DisplayName("Pruebas de consultas en IUsuario")
    void testConsultasUsuario() {
        Curso c = new Curso();
        c.nombre = "Curso Usuario Test";
        c.nivel = "FP Superior";
        c = cursoRepository.save(c);

        Usuario u = new Usuario();
        u.nombre = "Carlos Perez";
        u.email = "carlos_unique@safareyes.es";
        u.password = "secret";
        u.tipo = "ESTUDIANTE";
        u.estado = "ACTIVO";
        u.fechaInscripcion = new Timestamp(System.currentTimeMillis());
        u.curso = c;
        u = usuarioRepository.save(u);

        Optional<Usuario> porEmail = usuarioRepository.findByEmail("carlos_unique@safareyes.es");
        List<Usuario> porTipo = usuarioRepository.findByTipo("ESTUDIANTE");
        List<Usuario> porEstado = usuarioRepository.findByEstado("ACTIVO");
        List<Usuario> porCurso = usuarioRepository.findByCurso_Id(c.id);

        assertTrue(porEmail.isPresent());
        assertFalse(porTipo.isEmpty());
        assertFalse(porEstado.isEmpty());
        assertFalse(porCurso.isEmpty());
    }

    @Test
    @DisplayName("Pruebas de consultas en IEvento")
    void testConsultasEvento() {
        Curso c = new Curso();
        c.nombre = "Curso Evento";
        c.nivel = "FP Superior";
        c = cursoRepository.save(c);

        Usuario u = new Usuario();
        u.nombre = "Organizador Evento";
        u.email = "org_ev@safareyes.es";
        u.password = "secret";
        u.tipo = "ORGANIZADOR";
        u.estado = "ACTIVO";
        u.fechaInscripcion = new Timestamp(System.currentTimeMillis());
        u.curso = c;
        u = usuarioRepository.save(u);

        Categoria cat = new Categoria();
        cat.nombre = "Música";
        cat.icono = "music.png";
        cat.color = "#00FF00";
        cat = categoriaRepository.save(cat);

        Lugar l = new Lugar();
        l.nombre = "Teatro";
        l.aforoMax = 100;
        l = lugarRepository.save(l);

        Timestamp inicio = new Timestamp(System.currentTimeMillis());
        Timestamp fin = new Timestamp(System.currentTimeMillis() + 3600000);

        Evento e = new Evento();
        e.titulo = "Concierto Primavera";
        e.descripcion = "Musica en vivo";
        e.estado = "PUBLICADO";
        e.fechaInicio = inicio;
        e.fechaFin = fin;
        e.aforoMaximo = 100;
        e.creador = u;
        e.categoria = cat;
        e.lugar = l;
        e = eventoRepository.save(e);

        assertFalse(eventoRepository.findByCategoria_Id(cat.id).isEmpty());
        assertFalse(eventoRepository.findByCreador_Id(u.id).isEmpty());
        assertFalse(eventoRepository.findByLugar_Id(l.id).isEmpty());
        assertFalse(eventoRepository.findByTituloContainingIgnoreCase("Primavera").isEmpty());
        assertFalse(eventoRepository.findByEstado("PUBLICADO").isEmpty());
        assertFalse(eventoRepository.findByFechaInicio(inicio).isEmpty());
        assertFalse(eventoRepository.findByFechaFin(fin).isEmpty());
    }

    @Test
    @DisplayName("Pruebas de consultas en ICambio_evento")
    void testConsultasCambioEvento() {
        Evento e = crearEventoAuxiliar();

        Cambio_evento cambio = new Cambio_evento();
        cambio.evento = e;
        cambio.cambios = "Cambio de hora";
        cambio.fecha = new Timestamp(System.currentTimeMillis());
        cambioRepository.save(cambio);

        List<Cambio_evento> lista = cambioEventoRepository.findByEvento_Id(e.id);
        assertFalse(lista.isEmpty());
    }

    @Test
    @DisplayName("Pruebas de consultas en IEvento_curso")
    void testConsultasEventoCurso() {
        Evento e = crearEventoAuxiliar();
        Curso c = e.creador.curso;

        Evento_curso ec = new Evento_curso();
        ec.evento = e;
        ec.curso = c;
        eventoCursoRepository.save(ec);

        assertFalse(eventoCursoRepository.findByEvento_Id(e.id).isEmpty());
        assertFalse(eventoCursoRepository.findByCurso_Id(c.id).isEmpty());
    }

    @Test
    @DisplayName("Pruebas de consultas en IEvento_requisito")
    void testConsultasEventoRequisito() {
        Evento e = crearEventoAuxiliar();

        Requisitos r = new Requisitos();
        r.descripcion = "Llevar DNI";
        r = requisitosRepository.save(r);

        Evento_requisito er = new Evento_requisito();
        er.evento = e;
        er.requisito = r;
        eventoRequisitoRepository.save(er);

        assertFalse(eventoRequisitoRepository.findByEvento_Id(e.id).isEmpty());
        assertFalse(eventoRequisitoRepository.findByRequisito_Id(r.id).isEmpty());
    }

    @Test
    @DisplayName("Pruebas de consultas en IInscripcion")
    void testConsultasInscripcion() {
        Evento e = crearEventoAuxiliar();
        Usuario u = e.creador;

        Inscripcion ins = new Inscripcion();
        ins.usuario = u;
        ins.evento = e;
        ins.fechaInscripcion = new Timestamp(System.currentTimeMillis());
        ins.tipo = "ASISTIRE";
        ins.estado = "CONFIRMADA";
        ins.asistio = false;
        inscripcionRepository.save(ins);

        assertFalse(inscripcionRepository.findByUsuario_Id(u.id).isEmpty());
        assertFalse(inscripcionRepository.findByEvento_Id(e.id).isEmpty());
        assertFalse(inscripcionRepository.findByEstado("CONFIRMADA").isEmpty());
        assertFalse(inscripcionRepository.findByTipo("ASISTIRE").isEmpty());
    }

    @Test
    @DisplayName("Pruebas de consultas en INotificacion")
    void testConsultasNotificacion() {
        Evento e = crearEventoAuxiliar();
        Usuario u = e.creador;

        Notificacion n = new Notificacion();
        n.usuario = u;
        n.evento = e;
        n.tipo = "RECORDATORIO_24H";
        n.mensaje = "Recordatorio evento";
        n.fecha = new Timestamp(System.currentTimeMillis());
        n.leida = false;
        notificacionRepository.save(n);

        assertFalse(notificacionRepository.findByUsuario_Id(u.id).isEmpty());
        assertFalse(notificacionRepository.findByLeida(false).isEmpty());
        assertFalse(notificacionRepository.findByTipo("RECORDATORIO_24H").isEmpty());
    }

    @Test
    @DisplayName("Pruebas de consultas en IPublicacion")
    void testConsultasPublicacion() {
        Evento e = crearEventoAuxiliar();
        Usuario u = e.creador;

        Publicacion p = new Publicacion();
        p.evento = e;
        p.usuario = u;
        p.texto = "Novedades del evento";
        p.fecha = new Timestamp(System.currentTimeMillis());
        p = publicacionRepository.save(p);

        assertFalse(publicacionRepository.findByEvento_Id(e.id).isEmpty());
        assertFalse(publicacionRepository.findByAutor_Id(u.id).isEmpty());
    }

    @Test
    @DisplayName("Pruebas de consultas en IFoto_publicacion")
    void testConsultasFotoPublicacion() {
        Evento e = crearEventoAuxiliar();
        Usuario u = e.creador;

        Publicacion p = new Publicacion();
        p.evento = e;
        p.usuario = u;
        p.texto = "Publicacion con foto";
        p.fecha = new Timestamp(System.currentTimeMillis());
        p = publicacionRepository.save(p);

        Foto_publicacion f = new Foto_publicacion();
        f.publicacion = p;
        f.urlFoto = "https://midominio.es/foto.png";
        fotoPublicacionRepository.save(f);

        assertFalse(fotoPublicacionRepository.findByPublicacion_Id(p.id).isEmpty());
    }

    // Método auxiliar reutilizable para tests con jerarquías previas
    private Evento crearEventoAuxiliar() {
        long ts = System.currentTimeMillis();

        Curso c = new Curso();
        c.nombre = "Curso Auxiliar " + ts;
        c.nivel = "FP Superior";
        c = cursoRepository.save(c);

        Usuario u = new Usuario();
        u.nombre = "Usuario Aux " + ts;
        u.email = "aux_" + ts + "@safareyes.es";
        u.password = "secret";
        u.tipo = "ORGANIZADOR";
        u.estado = "ACTIVO";
        u.fechaInscripcion = new Timestamp(ts);
        u.curso = c;
        u = usuarioRepository.save(u);

        Categoria cat = new Categoria();
        cat.nombre = "Cat Aux " + ts;
        cat.icono = "icon.png";
        cat.color = "#000000";
        cat = categoriaRepository.save(cat);

        Lugar l = new Lugar();
        l.nombre = "Lugar Aux " + ts;
        l.aforoMax = 50;
        l = lugarRepository.save(l);

        Evento e = new Evento();
        e.titulo = "Evento Aux " + ts;
        e.descripcion = "Descripcion Aux";
        e.estado = "PUBLICADO";
        e.fechaInicio = new Timestamp(ts);
        e.fechaFin = new Timestamp(ts + 3600000);
        e.aforoMaximo = 50;
        e.creador = u;
        e.categoria = cat;
        e.lugar = l;
        return eventoRepository.save(e);
    }
}
}
