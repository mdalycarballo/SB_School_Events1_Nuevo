package es.safareyes.sb_school_events1_nuevo.servicios.impl;

import es.safareyes.sb_school_events1_nuevo.Modelos.Categoria;
import es.safareyes.sb_school_events1_nuevo.Modelos.Evento;
import es.safareyes.sb_school_events1_nuevo.Modelos.Lugar;
import es.safareyes.sb_school_events1_nuevo.Modelos.Usuario;
import es.safareyes.sb_school_events1_nuevo.Repositorios.ICategoria;
import es.safareyes.sb_school_events1_nuevo.Repositorios.IEvento;
import es.safareyes.sb_school_events1_nuevo.Repositorios.ILugar;
import es.safareyes.sb_school_events1_nuevo.Repositorios.IUsuario;
import es.safareyes.sb_school_events1_nuevo.dtos.request.ReqEventoDTO;
import es.safareyes.sb_school_events1_nuevo.dtos.response.ResEventoResumenDTO;
import es.safareyes.sb_school_events1_nuevo.mappers.EventoMapper;
import es.safareyes.sb_school_events1_nuevo.servicios.IEventoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
public class EventoServiceImpl implements IEventoService {

    private final IEvento eventoRepository;
    private final IUsuario usuarioRepository;
    private final ICategoria categoriaRepository;
    private final ILugar lugarRepository;
    private final EventoMapper eventoMapper;

    public EventoServiceImpl(IEvento eventoRepository,
                             IUsuario usuarioRepository,
                             ICategoria categoriaRepository,
                             ILugar lugarRepository,
                             EventoMapper eventoMapper) {
        this.eventoRepository = eventoRepository;
        this.usuarioRepository = usuarioRepository;
        this.categoriaRepository = categoriaRepository;
        this.lugarRepository = lugarRepository;
        this.eventoMapper = eventoMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResEventoResumenDTO> obtenerTodos() {
        List<Evento> eventos = eventoRepository.findAll();
        return eventos.stream()
                .map(eventoMapper::aResumenDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResEventoResumenDTO> obtenerPorEstado(String estado) {
        List<Evento> eventos = eventoRepository.findByEstado(estado);
        return eventos.stream()
                .map(eventoMapper::aResumenDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ResEventoResumenDTO obtenerPorId(Integer id) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado con ID: " + id));
        return eventoMapper.aResumenDTO(evento);
    }

    @Override
    @Transactional
    public ResEventoResumenDTO crearEvento(ReqEventoDTO dto, Integer creadorId) {
        Usuario creador = usuarioRepository.findById(creadorId)
                .orElseThrow(() -> new RuntimeException("Usuario creador no encontrado"));

        Categoria categoria = categoriaRepository.findById(dto.categoriaId().intValue())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        Lugar lugar = lugarRepository.findById(dto.lugarId().intValue())
                .orElseThrow(() -> new RuntimeException("Lugar no encontrado"));

        Evento evento = eventoMapper.aEntidad(dto);
        evento.creador = creador;
        evento.categoria = categoria;
        evento.lugar = lugar;
        evento.estado = "ACTIVO";

        Evento guardado = eventoRepository.save(evento);
        return eventoMapper.aResumenDTO(guardado);
    }

    @Override
    @Transactional
    public ResEventoResumenDTO actualizarEvento(Integer id, ReqEventoDTO dto) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado con ID: " + id));

        Categoria categoria = categoriaRepository.findById(dto.categoriaId().intValue())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        Lugar lugar = lugarRepository.findById(dto.lugarId().intValue())
                .orElseThrow(() -> new RuntimeException("Lugar no encontrado"));

        eventoMapper.actualizarEntidad(evento, dto);
        evento.categoria = categoria;
        evento.lugar = lugar;

        Evento actualizado = eventoRepository.save(evento);
        return eventoMapper.aResumenDTO(actualizado);
    }

    @Override
    @Transactional
    public void cambiarEstado(Integer id, String nuevoEstado) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));
        evento.estado = nuevoEstado;
        eventoRepository.save(evento);
    }

    @Override
    @Transactional
    public void eliminarEvento(Integer id) {
        if (!eventoRepository.existsById(id)) {
            throw new RuntimeException("Evento no encontrado");
        }
        eventoRepository.deleteById(id);
    }
}