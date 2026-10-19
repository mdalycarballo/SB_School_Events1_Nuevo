package es.safareyes.sb_school_events1_nuevo.Modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "requisitos")
public class Requisitos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer id;

    @Column(name = "descripcion")
    public String descripcion;
}
