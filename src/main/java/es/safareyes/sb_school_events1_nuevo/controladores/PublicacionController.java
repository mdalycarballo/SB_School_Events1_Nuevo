package es.safareyes.sb_school_events1_nuevo.controladores;

import es.safareyes.sb_school_events1_nuevo.dtos.request.ReqPublicacionDTO;
import es.safareyes.sb_school_events1_nuevo.dtos.response.ResPublicacionDTO;
import es.safareyes.sb_school_events1_nuevo.servicios.IPublicacionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionController {

    private final IPublicacionService publicacionService;

    public PublicacionController(IPublicacionService publicacionService) {
        this.publicacionService = publicacionService;
    }

    @GetMapping
    public ResponseEntity<List<ResPublicacionDTO>> obtenerTodas() {
        return ResponseEntity.ok(publicacionService.obtenerTodas());
    }

    @GetMapping("/evento/{eventoId}")
    public ResponseEntity<List<ResPublicacionDTO>> obtenerPorEvento(@PathVariable Integer eventoId) {
        return ResponseEntity.ok(publicacionService.obtenerPorEvento(eventoId));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<ResPublicacionDTO>> obtenerPorUsuario(@PathVariable Integer usuarioId) {
        return ResponseEntity.ok(publicacionService.obtenerPorUsuario(usuarioId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResPublicacionDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(publicacionService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<ResPublicacionDTO> crearPublicacion(@RequestParam Integer eventoId,
                                                              @RequestParam Integer usuarioId,
                                                              @Valid @RequestBody ReqPublicacionDTO dto) {
        ResPublicacionDTO creada = publicacionService.crearPublicacion(eventoId, usuarioId, dto);
        return new ResponseEntity<>(creada, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResPublicacionDTO> actualizarPublicacion(@PathVariable Integer id,
                                                                   @RequestParam Integer usuarioId,
                                                                   @Valid @RequestBody ReqPublicacionDTO dto) {
        return ResponseEntity.ok(publicacionService.actualizarPublicacion(id, dto, usuarioId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPublicacion(@PathVariable Integer id,
                                                    @RequestParam Integer usuarioId) {
        publicacionService.eliminarPublicacion(id, usuarioId);
        return ResponseEntity.noContent().build();
    }
}