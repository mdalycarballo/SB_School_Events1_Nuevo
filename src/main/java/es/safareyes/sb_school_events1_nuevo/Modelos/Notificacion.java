package es.safareyes.sb_school_events1_nuevo.Modelos;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "notificacion")
public class Notificacion {

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

    @Column(name = "tipo")
    public String tipo;

    @Column(name = "mensaje")
    public String mensaje;

    @Column(name = "fecha")
    public Timestamp fecha;

    @Column(name = "leida")
    public Boolean leida;

    @Override
    public String toString() {
        return "Notificacion{id=" + id + ", tipo='" + tipo + "', usuario=" + (usuario != null ? usuario.nombre : "null") + ", mensaje='" + mensaje + "', leida=" + leida + "}";
    }
}

