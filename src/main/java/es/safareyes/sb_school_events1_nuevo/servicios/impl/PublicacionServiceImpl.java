package es.safareyes.sb_school_events1_nuevo.servicios.impl;

import es.safareyes.sb_school_events1_nuevo.Modelos.Evento;
import es.safareyes.sb_school_events1_nuevo.Modelos.Publicacion;
import es.safareyes.sb_school_events1_nuevo.Modelos.Usuario;
import es.safareyes.sb_school_events1_nuevo.Repositorios.IEvento;
import es.safareyes.sb_school_events1_nuevo.Repositorios.IPublicacion;
import es.safareyes.sb_school_events1_nuevo.Repositorios.IUsuario;
import es.safareyes.sb_school_events1_nuevo.dtos.request.ReqPublicacionDTO;
import es.safareyes.sb_school_events1_nuevo.dtos.response.ResPublicacionDTO;
import es.safareyes.sb_school_events1_nuevo.mappers.PublicacionMapper;
import es.safareyes.sb_school_events1_nuevo.servicios.IPublicacionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PublicacionServiceImpl implements IPublicacionService {

    private final IPublicacion publicacionRepository;
    private final IEvento eventoRepository;
    private final IUsuario usuarioRepository;
    private final PublicacionMapper publicacionMapper;

    public PublicacionServiceImpl(IPublicacion publicacionRepository,
                                  IEvento eventoRepository,
                                  IUsuario usuarioRepository,
                                  PublicacionMapper publicacionMapper) {
        this.publicacionRepository = publicacionRepository;
        this.eventoRepository = eventoRepository;
        this.usuarioRepository = usuarioRepository;
        this.publicacionMapper = publicacionMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResPublicacionDTO> obtenerTodas() {
        List<Publicacion> publicaciones = publicacionRepository.findAll();
        return publicacionMapper.aResponseList(publicaciones);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResPublicacionDTO> obtenerPorEvento(Integer eventoId) {
        List<Publicacion> publicaciones = publicacionRepository.findByEvento_Id(eventoId);
        return publicacionMapper.aResponseList(publicaciones);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResPublicacionDTO> obtenerPorUsuario(Integer usuarioId) {
        List<Publicacion> publicaciones = publicacionRepository.findByUsuario_Id(usuarioId);
        return publicacionMapper.aResponseList(publicaciones);
    }

    @Override
    @Transactional(readOnly = true)
    public ResPublicacionDTO obtenerPorId(Integer id) {
        Publicacion publicacion = publicacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publicación no encontrada con ID: " + id));
        return publicacionMapper.aResponse(publicacion);
    }

    @Override
    @Transactional
    public ResPublicacionDTO crearPublicacion(Integer eventoId, Integer usuarioId, ReqPublicacionDTO dto) {
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado con ID: " + eventoId));

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + usuarioId));

        Publicacion publicacion = publicacionMapper.aEntidad(dto);
        publicacion.evento = evento;
        publicacion.usuario = usuario;
        publicacion.fecha = Timestamp.valueOf(LocalDateTime.now());

        Publicacion guardada = publicacionRepository.save(publicacion);
        return publicacionMapper.aResponse(guardada);
    }

    @Override
    @Transactional
    public ResPublicacionDTO actualizarPublicacion(Integer id, ReqPublicacionDTO dto, Integer usuarioId) {
        Publicacion publicacion = publicacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publicación no encontrada con ID: " + id));

        if (!publicacion.usuario.id.equals(usuarioId)) {
            throw new RuntimeException("No tienes permisos para editar esta publicación");
        }

        publicacionMapper.actualizarEntidad(publicacion, dto);
        Publicacion actualizada = publicacionRepository.save(publicacion);
        return publicacionMapper.aResponse(actualizada);
    }

    @Override
    @Transactional
    public void eliminarPublicacion(Integer id, Integer usuarioId) {
        Publicacion publicacion = publicacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publicación no encontrada con ID: " + id));

        if (!publicacion.usuario.id.equals(usuarioId)) {
            throw new RuntimeException("No tienes permisos para eliminar esta publicación");
        }

        publicacionRepository.delete(publicacion);
    }
}