package es.safareyes.sb_school_events1_nuevo.mappers;


import org.springframework.web.bind.annotation.Mapping;

//@Mapper(componentModel1)
public interface prueba { //Se llamara PruebaMapper

    @Mapping(source = "curso.evento", target = "evento")

    PruebaDTO toDto(Prueba prueba);

    List<PruebaDTO> toDto(List<Prueba> prueba)

}
