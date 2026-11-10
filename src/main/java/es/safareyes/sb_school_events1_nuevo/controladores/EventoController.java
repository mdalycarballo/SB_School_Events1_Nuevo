package es.safareyes.sb_school_events1_nuevo.controladores;

import es.safareyes.sb_school_events1_nuevo.dtos.request.ReqEventoDTO;
import es.safareyes.sb_school_events1_nuevo.dtos.response.ResEventoResumenDTO;
import es.safareyes.sb_school_events1_nuevo.servicios.IEventoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    private final IEventoService eventoService;

    public EventoController(IEventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping
    public ResponseEntity<List<ResEventoResumenDTO>> obtenerTodos() {
        return ResponseEntity.ok(eventoService.obtenerTodos());
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<ResEventoResumenDTO>> obtenerPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(eventoService.obtenerPorEstado(estado));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResEventoResumenDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(eventoService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<ResEventoResumenDTO> crearEvento(@Valid @RequestBody ReqEventoDTO dto,
                                                           @RequestParam Integer creadorId) {
        ResEventoResumenDTO creado = eventoService.crearEvento(dto, creadorId);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResEventoResumenDTO> actualizarEvento(@PathVariable Integer id,
                                                                @Valid @RequestBody ReqEventoDTO dto) {
        return ResponseEntity.ok(eventoService.actualizarEvento(id, dto));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Void> cambiarEstado(@PathVariable Integer id,
                                              @RequestParam String estado) {
        eventoService.cambiarEstado(id, estado);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEvento(@PathVariable Integer id) {
        eventoService.eliminarEvento(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/test")
    public String pruebaConexion() {
        return "OK - El controlador funciona correctamente";
    }

}