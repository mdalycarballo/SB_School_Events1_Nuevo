package es.safareyes.sb_school_events1_nuevo.Modelos;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "inscripcion")
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    public Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    public Evento evento;

    @Column(name = "fecha")
    public Timestamp fechaInscripcion;

    @Column(name = "estado")
    public String estado;

    @Column(name = "tipo")
    public String tipo;

    @Column(name = "asistio")
    public Boolean asistio;

    // Requerido por JPA
    public Inscripcion() {
    }

    @Override
    public String toString() {
        return "Inscripcion{id=" + id + ", usuario=" + (usuario != null ? usuario.nombre : "null") + ", evento=" + (evento != null ? evento.titulo : "null") + ", estado='" + estado + "', tipo='" + tipo + "'}";
    }
}