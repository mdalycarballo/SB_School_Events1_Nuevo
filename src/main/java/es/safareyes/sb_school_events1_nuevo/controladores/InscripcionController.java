package es.safareyes.sb_school_events1_nuevo.controladores;

import es.safareyes.sb_school_events1_nuevo.dtos.request.ReqInscripcionDTO;
import es.safareyes.sb_school_events1_nuevo.dtos.response.ResInscripcionDTO;
import es.safareyes.sb_school_events1_nuevo.servicios.IInscripcionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionController {

    private final IInscripcionService inscripcionService;

    public InscripcionController(IInscripcionService inscripcionService) {
        this.inscripcionService = inscripcionService;
    }

    @PostMapping
    public ResponseEntity<ResInscripcionDTO> inscribirse(@Valid @RequestBody ReqInscripcionDTO dto,
                                                         @RequestParam Integer usuarioId) {
        ResInscripcionDTO inscripcion = inscripcionService.inscribirse(dto, usuarioId);
        return new ResponseEntity<>(inscripcion, HttpStatus.CREATED);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<ResInscripcionDTO>> obtenerPorUsuario(@PathVariable Integer usuarioId) {
        return ResponseEntity.ok(inscripcionService.obtenerPorUsuario(usuarioId));
    }

    @GetMapping("/evento/{eventoId}")
    public ResponseEntity<List<ResInscripcionDTO>> obtenerPorEvento(@PathVariable Integer eventoId) {
        return ResponseEntity.ok(inscripcionService.obtenerPorEvento(eventoId));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<ResInscripcionDTO> cambiarEstado(@PathVariable Integer id,
                                                           @RequestParam String estado) {
        return ResponseEntity.ok(inscripcionService.cambiarEstado(id, estado));
    }

    @PatchMapping("/{id}/asistencia")
    public ResponseEntity<ResInscripcionDTO> registrarAsistencia(@PathVariable Integer id,
                                                                 @RequestParam Boolean asistio) {
        return ResponseEntity.ok(inscripcionService.registrarAsistencia(id, asistio));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarInscripcion(@PathVariable Integer id,
                                                    @RequestParam Integer usuarioId) {
        inscripcionService.cancelarInscripcion(id, usuarioId);
        return ResponseEntity.noContent().build();
    }
}