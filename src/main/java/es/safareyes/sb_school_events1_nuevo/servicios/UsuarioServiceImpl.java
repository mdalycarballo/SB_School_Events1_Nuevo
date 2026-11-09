package es.safareyes.sb_school_events1_nuevo.servicios.impl;

import es.safareyes.sb_school_events1_nuevo.Modelos.Curso;
import es.safareyes.sb_school_events1_nuevo.Modelos.Usuario;
import es.safareyes.sb_school_events1_nuevo.dtos.request.ReqUsuarioDTO;
import es.safareyes.sb_school_events1_nuevo.dtos.response.ResUsuarioDTO;
import es.safareyes.sb_school_events1_nuevo.exception.ResourceAlreadyExistsException;
import es.safareyes.sb_school_events1_nuevo.exception.ResourceNotFoundException;
import es.safareyes.sb_school_events1_nuevo.mappers.UsuarioMapper;
import es.safareyes.sb_school_events1_nuevo.Repositorios.ICurso;
import es.safareyes.sb_school_events1_nuevo.Repositorios.IUsuario;
import es.safareyes.sb_school_events1_nuevo.servicios.IUsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class UsuarioServiceImpl implements IUsuarioService {

    private final IUsuario usuarioRepository;
    private final ICurso cursoRepository;
    private final UsuarioMapper usuarioMapper;

    @Override
    public List<ResUsuarioDTO> listar() {
        return usuarioMapper.aResponseList(usuarioRepository.findAll());
    }

    @Override
    public ResUsuarioDTO buscarPorId(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id));
        return usuarioMapper.aResponse(usuario);
    }

    @Override
    @Transactional
    public ResUsuarioDTO crear(ReqUsuarioDTO request) {
        if (usuarioRepository.existsByNombre(request.username())) {
            throw new ResourceAlreadyExistsException("Ya existe un usuario con el nombre: " + request.username());
        }

        Usuario usuario = usuarioMapper.aEntidad(request);
        usuario.nombre = request.username();
        usuario.estado = "ACTIVO";
        usuario.fechaInscripcion = Timestamp.from(Instant.now());

        if (request.cursoId() != null) {
            Curso curso = cursoRepository.findById(request.cursoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Curso", "id", request.cursoId()));
            usuario.curso = curso;
        }

        Usuario guardado = usuarioRepository.save(usuario);
        log.info("Usuario creado exitosamente con ID: {}", guardado.id);
        return usuarioMapper.aResponse(guardado);
    }

    @Override
    @Transactional
    public ResUsuarioDTO actualizar(Integer id, ReqUsuarioDTO request) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id));

        if (request.username() != null) {
            usuario.nombre = request.username();
        }

        if (request.cursoId() != null) {
            Curso curso = cursoRepository.findById(request.cursoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Curso", "id", request.cursoId()));
            usuario.curso = curso;
        }

        log.info("Usuario actualizado con ID: {}", id);
        return usuarioMapper.aResponse(usuario);
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id));
        usuarioRepository.delete(usuario);
        log.info("Usuario eliminado con ID: {}", id);
    }
}