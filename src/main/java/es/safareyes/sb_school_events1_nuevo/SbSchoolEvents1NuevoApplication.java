package es.safareyes.sb_school_events1_nuevo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "es.safareyes.sb_school_events1_nuevo")
public class SbSchoolEvents1NuevoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbSchoolEvents1NuevoApplication.class, args);
    }
}