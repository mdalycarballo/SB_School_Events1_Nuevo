package es.safareyes.sb_school_events1_nuevo.dtos;

import es.safareyes.sb_school_events1_nuevo.Modelos.Categoria;
import es.safareyes.sb_school_events1_nuevo.Modelos.Lugar;
import es.safareyes.sb_school_events1_nuevo.Modelos.Usuario;
import lombok.Data;
import java.sql.Timestamp;

@Data
public class EventoDTO {
    private Integer id;
    private String titulo;
    private String descripcion;
    private String estado;
    private Timestamp fechaInicio;
    private Timestamp fechaFin;
    private Integer aforoMaximo;
    private Usuario creador;
    private Categoria categoria;
    private Lugar lugar;
}
