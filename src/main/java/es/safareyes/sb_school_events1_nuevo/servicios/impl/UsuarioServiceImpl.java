package es.safareyes.sb_school_events1_nuevo.servicios.impl;

import es.safareyes.sb_school_events1_nuevo.Modelos.Curso;
import es.safareyes.sb_school_events1_nuevo.Modelos.Usuario;
import es.safareyes.sb_school_events1_nuevo.Repositorios.ICurso;
import es.safareyes.sb_school_events1_nuevo.Repositorios.IUsuario;
import es.safareyes.sb_school_events1_nuevo.dtos.request.ReqUsuarioDTO;
import es.safareyes.sb_school_events1_nuevo.dtos.response.ResUsuarioDTO;
import es.safareyes.sb_school_events1_nuevo.mappers.UsuarioMapper;
import es.safareyes.sb_school_events1_nuevo.servicios.IUsuarioService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    private final IUsuario usuarioRepository;
    private final ICurso cursoRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(IUsuario usuarioRepository,
                              ICurso cursoRepository,
                              UsuarioMapper usuarioMapper,
                              PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
        this.usuarioMapper = usuarioMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResUsuarioDTO> obtenerTodos() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarioMapper.aResponseList(usuarios);
    }

    @Override
    @Transactional(readOnly = true)
    public ResUsuarioDTO obtenerPorId(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
        return usuarioMapper.aResponse(usuario);
    }

    @Override
    @Transactional
    public ResUsuarioDTO registrarUsuario(ReqUsuarioDTO dto) {
        if (usuarioRepository.existsByEmail(dto.email())) {
            throw new RuntimeException("El email ya está registrado");
        }

        Usuario usuario = usuarioMapper.aEntidad(dto);
        usuario.nombre = dto.username();
        usuario.password = passwordEncoder.encode(dto.password());
        usuario.tipo = "ALUMNO";
        usuario.estado = "ACTIVO";
        usuario.fechaInscripcion = Timestamp.valueOf(LocalDateTime.now());

        if (dto.cursoId() != null) {
            Curso curso = cursoRepository.findById(dto.cursoId().intValue())
                    .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
            usuario.curso = curso;
        }

        Usuario guardado = usuarioRepository.save(usuario);
        return usuarioMapper.aResponse(guardado);
    }

    @Override
    @Transactional
    public ResUsuarioDTO actualizarUsuario(Integer id, ReqUsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        if (!usuario.email.equalsIgnoreCase(dto.email()) && usuarioRepository.existsByEmail(dto.email())) {
            throw new RuntimeException("El email ya pertenece a otro usuario");
        }

        usuario.nombre = dto.username();
        usuario.email = dto.email();

        if (dto.password() != null && !dto.password().isBlank()) {
            usuario.password = passwordEncoder.encode(dto.password());
        }

        if (dto.cursoId() != null) {
            Curso curso = cursoRepository.findById(dto.cursoId().intValue())
                    .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
            usuario.curso = curso;
        } else {
            usuario.curso = null;
        }

        Usuario actualizado = usuarioRepository.save(usuario);
        return usuarioMapper.aResponse(actualizado);
    }

    @Override
    @Transactional
    public void cambiarEstado(Integer id, String nuevoEstado) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.estado = nuevoEstado;
        usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public void eliminarUsuario(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        usuarioRepository.deleteById(id);
    }
}