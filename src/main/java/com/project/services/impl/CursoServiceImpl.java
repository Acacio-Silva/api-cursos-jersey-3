package com.project.services.impl;

import com.project.dao.impl.CursoDAOImpl;
import com.project.models.aluno.Aluno;
import com.project.models.aluno.AlunoRequest;
import com.project.models.curso.Curso;
import com.project.models.curso.CursoRequest;
import com.project.models.curso.CursoResponse;
import com.project.services.CursoService;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class CursoServiceImpl implements CursoService {

    private ModelMapper mapper = new ModelMapper();
    private CursoDAOImpl cursoDAO = new CursoDAOImpl();

    @Override
    public CursoResponse save(CursoRequest cursoRequest) {
        Curso curso = cursoDAO.save(mapper.map(cursoRequest, Curso.class));
        return mapper.map(curso, CursoResponse.class);
    }

    @Override
    public List<CursoResponse> findAll() {
        System.out.println("chegou aqui");
        return cursoDAO.findAll().stream()
                .map(e-> mapper.map(e, CursoResponse.class)).collect(Collectors.toList());
    }

    @Override
    public CursoRequest findById(Integer id) {
        Curso curso = cursoDAO.findById(id);
        if(curso == null){
            throw new RuntimeException("Curso não encontrado!");
        }
        return mapper.map(curso, CursoRequest.class);
    }

    @Override
    public CursoResponse update(Integer id, CursoRequest cursoRequest) {
        Curso curso = cursoDAO.update(id, mapper.map(cursoRequest, Curso.class));
        return mapper.map(curso, CursoResponse.class);
    }

    @Override
    public void remove(Integer id) {
        Curso curso = cursoDAO.findById(id);
        if(curso == null){
            throw new RuntimeException("Curso não encontrado!");
        }
        cursoDAO.remove(id);
    }

}
