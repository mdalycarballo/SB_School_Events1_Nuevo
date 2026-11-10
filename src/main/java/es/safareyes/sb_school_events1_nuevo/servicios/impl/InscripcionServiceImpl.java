package es.safareyes.sb_school_events1_nuevo.servicios.impl;

import es.safareyes.sb_school_events1_nuevo.Modelos.Evento;
import es.safareyes.sb_school_events1_nuevo.Modelos.Inscripcion;
import es.safareyes.sb_school_events1_nuevo.Modelos.Usuario;
import es.safareyes.sb_school_events1_nuevo.Repositorios.IEvento;
import es.safareyes.sb_school_events1_nuevo.Repositorios.IInscripcion;
import es.safareyes.sb_school_events1_nuevo.Repositorios.IUsuario;
import es.safareyes.sb_school_events1_nuevo.dtos.request.ReqInscripcionDTO;
import es.safareyes.sb_school_events1_nuevo.dtos.response.ResInscripcionDTO;
import es.safareyes.sb_school_events1_nuevo.mappers.InscripcionMapper;
import es.safareyes.sb_school_events1_nuevo.servicios.IInscripcionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class InscripcionServiceImpl implements IInscripcionService {

    private final IInscripcion inscripcionRepository;
    private final IEvento eventoRepository;
    private final IUsuario usuarioRepository;
    private final InscripcionMapper inscripcionMapper;

    public InscripcionServiceImpl(IInscripcion inscripcionRepository,
                                  IEvento eventoRepository,
                                  IUsuario usuarioRepository,
                                  InscripcionMapper inscripcionMapper) {
        this.inscripcionRepository = inscripcionRepository;
        this.eventoRepository = eventoRepository;
        this.usuarioRepository = usuarioRepository;
        this.inscripcionMapper = inscripcionMapper;
    }

    @Override
    @Transactional
    public ResInscripcionDTO inscribirse(ReqInscripcionDTO dto, Integer usuarioId) {
        Integer eventoId = dto.eventoId().intValue();

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));

        // Validar si ya está inscrito
        if (inscripcionRepository.findByUsuario_IdAndEvento_Id(usuarioId, eventoId).isPresent()) {
            throw new RuntimeException("El usuario ya está inscrito en este evento");
        }

        // Validar aforo máximo solo si el tipo es ASISTIRE
        if ("ASISTIRE".equalsIgnoreCase(dto.tipo()) && evento.aforoMaximo != null) {
            long confirmados = inscripcionRepository.countByEvento_IdAndEstado(eventoId, "CONFIRMADA");
            if (confirmados >= evento.aforoMaximo) {
                throw new RuntimeException("El aforo máximo para este evento ha sido alcanzado");
            }
        }

        Inscripcion inscripcion = new Inscripcion();
        inscripcion.usuario = usuario;
        inscripcion.evento = evento;
        inscripcion.tipo = dto.tipo();
        inscripcion.estado = "CONFIRMADA";
        inscripcion.asistio = false;
        inscripcion.fechaInscripcion = Timestamp.valueOf(LocalDateTime.now());

        Inscripcion guardada = inscripcionRepository.save(inscripcion);
        return inscripcionMapper.aResponse(guardada);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResInscripcionDTO> obtenerPorUsuario(Integer usuarioId) {
        List<Inscripcion> inscripciones = inscripcionRepository.findByUsuario_Id(usuarioId);
        return inscripcionMapper.aResponseList(inscripciones);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResInscripcionDTO> obtenerPorEvento(Integer eventoId) {
        List<Inscripcion> inscripciones = inscripcionRepository.findByEvento_Id(eventoId);
        return inscripcionMapper.aResponseList(inscripciones);
    }

    @Override
    @Transactional
    public ResInscripcionDTO cambiarEstado(Integer id, String nuevoEstado) {
        Inscripcion inscripcion = inscripcionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inscripción no encontrada"));
        inscripcion.estado = nuevoEstado;
        return inscripcionMapper.aResponse(inscripcionRepository.save(inscripcion));
    }

    @Override
    @Transactional
    public ResInscripcionDTO registrarAsistencia(Integer id, Boolean asistio) {
        Inscripcion inscripcion = inscripcionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inscripción no encontrada"));
        inscripcion.asistio = asistio;
        return inscripcionMapper.aResponse(inscripcionRepository.save(inscripcion));
    }

    @Override
    @Transactional
    public void cancelarInscripcion(Integer id, Integer usuarioId) {
        Inscripcion inscripcion = inscripcionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inscripción no encontrada"));

        if (!inscripcion.usuario.id.equals(usuarioId)) {
            throw new RuntimeException("No tienes permisos para cancelar esta inscripción");
        }

        inscripcion.estado = "CANCELADA";
        inscripcionRepository.save(inscripcion);
    }
}