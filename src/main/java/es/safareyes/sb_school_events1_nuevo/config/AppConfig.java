package es.safareyes.sb_school_events1_nuevo.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "es.safareyes.sb_school_events1_nuevo")
@EnableJpaRepositories(basePackages = {
        "es.safareyes.sb_school_events1_nuevo.Repositorios",
        "es.safareyes.sb_school_events1_nuevo.repositorios"
})
@EntityScan(basePackages = {
        "es.safareyes.sb_school_events1_nuevo.Modelos",
        "es.safareyes.sb_school_events1_nuevo.modelos"
})
public class AppConfig {
}