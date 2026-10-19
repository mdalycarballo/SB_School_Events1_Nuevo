package es.safareyes.sb_school_events1_nuevo.Modelos;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "cambio_evento", schema="public")
public class Cambio_evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evento_id", nullable = false)
    public Evento evento;

    @Column(name = "cambios")
    public String cambios;

    @Column(name = "fecha")
    public Timestamp fecha;

    @Override
    public String toString() {
        return "Cambio_evento{id=" + id + ", cambios='" + cambios + "', fecha=" + fecha + ", evento=" + (evento != null ? evento.titulo : "null") + "}";
    }
}