package es.safareyes.sb_school_events1_nuevo;

import es.safareyes.sb_school_events1_nuevo.controladores.EventoController;
import es.safareyes.sb_school_events1_nuevo.mappers.EventoMapperImpl;
import es.safareyes.sb_school_events1_nuevo.servicios.impl.EventoServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {
        "es.safareyes.sb_school_events1_nuevo.Repositorios",
        "es.safareyes.sb_school_events1_nuevo.repositorios"
})
@EntityScan(basePackages = {
        "es.safareyes.sb_school_events1_nuevo.Modelos",
        "es.safareyes.sb_school_events1_nuevo.modelos"
})
@Import({EventoController.class, EventoServiceImpl.class, EventoMapperImpl.class})
public class SbSchoolEvents1NuevoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbSchoolEvents1NuevoApplication.class, args);
    }
}