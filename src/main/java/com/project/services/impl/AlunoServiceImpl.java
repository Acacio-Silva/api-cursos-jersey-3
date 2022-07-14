package com.project.services.impl;

import com.project.dao.impl.AlunoDAOImpl;
import com.project.dao.impl.CursoDAOImpl;
import com.project.exceptions.DataIntegrityViolation;
import com.project.exceptions.NotFoundException;
import com.project.models.aluno.Aluno;
import com.project.models.aluno.AlunoRequest;
import com.project.models.aluno.AlunoResponse;
import com.project.models.curso.Curso;
import com.project.models.curso.CursoRequest;
import com.project.services.AlunoService;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class AlunoServiceImpl implements AlunoService {

    private ModelMapper mapper = new ModelMapper();
    private AlunoDAOImpl alunoDAO = new AlunoDAOImpl();
    private CursoDAOImpl cursoDAO = new CursoDAOImpl();

    @Override
    public List<AlunoResponse> findAll() {
        return alunoDAO.findAll().stream()
                .map(e-> mapper.map(e, AlunoResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public AlunoRequest findById(Integer id) {
        Aluno aluno = alunoDAO.findById(id);
        if(aluno == null){
            throw new RuntimeException("Usuario não encontrado!");
        }
        return mapper.map(aluno, AlunoRequest.class);
    }

    @Override
    public AlunoRequest findByCpf(String cpf) {
        Aluno aluno = alunoDAO.findByCpf(cpf);
        if(aluno == null){
            throw new RuntimeException("Usuario não encontrado!");
        }
        return mapper.map(aluno, AlunoRequest.class);
    }

    @Override
    public AlunoResponse save(AlunoRequest alunoRequest) {
        Aluno aluno = alunoDAO.save(mapper.map(alunoRequest, Aluno.class));
        return mapper.map(aluno, AlunoResponse.class);
    }

    @Override
    public AlunoResponse update(Integer id, AlunoRequest alunoRequest) {
        findById(id);
        Aluno aluno = alunoDAO.update(id, mapper.map(alunoRequest, Aluno.class));
        return mapper.map(aluno, AlunoResponse.class);
    }

    @Override
    public void remove(Integer id) {
        Aluno aluno = alunoDAO.findById(id);
        if(aluno == null){
            throw new NotFoundException("Usuario não encontrado com id: "+id);
        }
        System.out.println(id);
        alunoDAO.remove(id);
    }

    public AlunoRequest matricula(Integer idAluno, Integer idCurso){
        Aluno aluno = alunoDAO.findById(idAluno);
        Curso curso = cursoDAO.findById(idCurso);
        if(aluno == null){
            throw new NotFoundException("Aluno não encontrado no id: "+idAluno);
        }
        if(curso.getQuantidadeAlunos() < (curso.getAlunos().size()+1)){
            throw new DataIntegrityViolation("Turma com capacidade maxima preenchida!");
        }
        aluno.setCurso(curso);
        alunoDAO.update(idAluno,aluno);
        return mapper.map(aluno, AlunoRequest.class);

    }
}
