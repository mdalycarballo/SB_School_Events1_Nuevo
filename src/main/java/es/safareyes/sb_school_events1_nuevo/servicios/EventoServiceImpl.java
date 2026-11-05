package es.safareyes.sb_school_events1_nuevo.servicios;

import es.safareyes.sb_school_events1_nuevo.Modelos.Categoria;
import es.safareyes.sb_school_events1_nuevo.Modelos.Evento;
import es.safareyes.sb_school_events1_nuevo.Modelos.Lugar;
import es.safareyes.sb_school_events1_nuevo.dtos.request.ReqEventoDTO;
import es.safareyes.sb_school_events1_nuevo.dtos.response.ResEventoDetalleDTO;
import es.safareyes.sb_school_events1_nuevo.dtos.response.ResEventoResumenDTO;
import es.safareyes.sb_school_events1_nuevo.exception.BadRequestException;
import es.safareyes.sb_school_events1_nuevo.exception.ResourceNotFoundException;
import es.safareyes.sb_school_events1_nuevo.mappers.EventoMapper;
import es.safareyes.sb_school_events1_nuevo.Repositorios.ICategoria;
import es.safareyes.sb_school_events1_nuevo.Repositorios.IEvento;
import es.safareyes.sb_school_events1_nuevo.Repositorios.ILugar;
import es.safareyes.sb_school_events1_nuevo.servicios.IEventoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class EventoServiceImpl implements IEventoService {

    private final IEvento eventoRepository;
    private final ICategoria categoriaRepository;
    private final ILugar lugarRepository;
    private final EventoMapper eventoMapper;

    @Override
    public List<ResEventoResumenDTO> listar(Integer categoriaId, String estado) {
        List<Evento> eventos;
        if (categoriaId != null) {
            eventos = eventoRepository.findByCategoriaId(categoriaId);
        } else if (estado != null && !estado.isBlank()) {
            eventos = eventoRepository.findByEstado(estado);
        } else {
            eventos = eventoRepository.findAll();
        }
        return eventos.stream()
                .map(eventoMapper::aResumenDTO)
                .toList();
    }

    @Override
    public ResEventoDetalleDTO buscarPorId(Integer id) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento", "id", id));
        return eventoMapper.aDetalleDTO(evento);
    }

    @Override
    @Transactional
    public ResEventoResumenDTO crear(ReqEventoDTO request) {
        // Regla de Negocio: La fecha de inicio debe ser anterior a la de fin
        if (request.inicio() != null && request.fin() != null && request.inicio().isAfter(request.fin())) {
            throw new BadRequestException("La fecha de inicio no puede ser posterior a la fecha de fin");
        }

        Evento evento = eventoMapper.aEntidad(request);

        if (request.idCategoria() != null) {
            Categoria categoria = categoriaRepository.findById(request.idCategoria())
                    .orElseThrow(() -> new ResourceNotFoundException("Categoría", "id", request.idCategoria()));
            evento.setCategoria(categoria);
        }

        if (request.idLugar() != null) {
            Lugar lugar = lugarRepository.findById(request.idLugar())
                    .orElseThrow(() -> new ResourceNotFoundException("Lugar", "id", request.idLugar()));
            evento.setLugar(lugar);
        }

        evento.setEstado("DISPONIBLE");
        Evento guardado = eventoRepository.save(evento);
        log.info("Evento creado exitosamente con ID: {}", guardado.getId());

        return eventoMapper.aResumenDTO(guardado);
    }

    @Override
    @Transactional
    public ResEventoResumenDTO actualizar(Integer id, ReqEventoDTO request) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento", "id", id));

        eventoMapper.actualizarEntidad(evento, request);

        if (request.idCategoria() != null) {
            Categoria categoria = categoriaRepository.findById(request.idCategoria())
                    .orElseThrow(() -> new ResourceNotFoundException("Categoría", "id", request.idCategoria()));
            evento.setCategoria(categoria);
        }

        if (request.idLugar() != null) {
            Lugar lugar = lugarRepository.findById(request.idLugar())
                    .orElseThrow(() -> new ResourceNotFoundException("Lugar", "id", request.idLugar()));
            evento.setLugar(lugar);
        }

        log.info("Evento actualizado exitosamente con ID: {}", id);
        return eventoMapper.aResumenDTO(evento); // Hibernate aplica Dirty Checking automáticamente
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento", "id", id));
        eventoRepository.delete(evento);
        log.info("Evento eliminado con ID: {}", id);
    }
}