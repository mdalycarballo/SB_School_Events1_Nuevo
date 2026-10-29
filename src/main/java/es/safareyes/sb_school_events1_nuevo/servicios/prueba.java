package es.safareyes.sb_school_events1_nuevo.servicios;


import es.safareyes.sb_school_events1_nuevo.Modelos.Curso;
import es.safareyes.sb_school_events1_nuevo.Repositorios.ICurso;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

public class prueba {//CursoService

    private ICurso cursoRepository;

    @Service
    @GetMapping("/todas")
    public List<Curso> consultarCurso(){
        return cursoRepository.consultarCurso();
    }

    @Service
    @GetMapping("/id")
    public List<Curso> consultarCurso(){
        return cursoRepository.consultarCurso();
    }


//deberia de quedar asi para trabajar con todos, despues se 

    public List<Curso> consultaCurso(){
        List<Curso> curso = CursoRepository.findAll();
        List<Curso> dtos = new ArrayList<>;


        for(Curso e: curso){
            CursoResponse dto = UsuarioMapper.aResponse(Curso);
            ListaResponse.add(dto);
        }
        return ListaRespuesta;
    }


}
