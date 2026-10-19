package es.safareyes.sb_school_events1_nuevo.Modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre")
    public String nombre;

    @Column(name = "icono")
    public String icono;

    @Column(name = "color")
    public String color;

    @Override
    public String toString() {
        return "Categoria{id=" + id + ", nombre='" + nombre + "', icono='" + icono + "', color='" + color + "'}";
    }
}