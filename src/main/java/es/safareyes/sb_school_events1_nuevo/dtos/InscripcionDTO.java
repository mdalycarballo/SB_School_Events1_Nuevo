package es.safareyes.sb_school_events1_nuevo.dtos;
import es.safareyes.sb_school_events1_nuevo.Modelos.Evento;
import es.safareyes.sb_school_events1_nuevo.Modelos.Usuario;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class InscripcionDTO {
    private Integer id;
    private Usuario usuario;
    private Evento evento;
    private Timestamp fechaInscripcion;
    private String estado;
    private String tipo;
    private Boolean asistio;
}
