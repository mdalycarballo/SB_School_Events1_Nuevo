package es.safareyes.sb_school_events1_nuevo.dtos;

import es.safareyes.sb_school_events1_nuevo.Modelos.Curso;
import es.safareyes.sb_school_events1_nuevo.Modelos.Evento;
import lombok.Data;

@Data
public class Evento_cursoDTO {
    private Integer id;
    private Evento evento;
    private Curso curso;
}
