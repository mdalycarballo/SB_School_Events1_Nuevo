package es.safareyes.sb_school_events1_nuevo;

import es.safareyes.sb_school_events1_nuevo.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(AppConfig.class)
public class SbSchoolEvents1NuevoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbSchoolEvents1NuevoApplication.class, args);
    }
}