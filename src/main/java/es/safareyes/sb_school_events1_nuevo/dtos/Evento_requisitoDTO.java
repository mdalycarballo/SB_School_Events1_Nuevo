package es.safareyes.sb_school_events1_nuevo.dtos;
import es.safareyes.sb_school_events1_nuevo.Modelos.Evento;
import es.safareyes.sb_school_events1_nuevo.Modelos.Requisitos;
import lombok.Data;

@Data
public class Evento_requisitoDTO {
    private Integer id;
    private Evento evento;
    private Requisitos requisito;
}
