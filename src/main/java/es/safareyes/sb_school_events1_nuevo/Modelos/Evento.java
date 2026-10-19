package es.safareyes.sb_school_events1_nuevo.Modelos;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "evento")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer id;

    @Column(name = "titulo")
    public String titulo;

    @Column(name = "descripcion")
    public String descripcion;

    @Column(name = "estado")
    public String estado;

    @Column(name = "fecha_inicio")
    public Timestamp fechaInicio;

    @Column(name = "fecha_fin")
    public Timestamp fechaFin;

    @Column(name = "aforo_maximo")
    public Integer aforoMaximo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creador_id", nullable = false)
    public Usuario creador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false)
    public Categoria categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lugar_id", nullable = false)
    public Lugar lugar;

    @Override
    public String toString() {
        return "Evento{id=" + id + ", titulo='" + titulo + "', estado='" + estado + "', creador=" + (creador != null ? creador.nombre : "null") + ", categoria=" + (categoria != null ? categoria.nombre : "null") + ", lugar=" + (lugar != null ? lugar.nombre : "null") + "}";
    }
}