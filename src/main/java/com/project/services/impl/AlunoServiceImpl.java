package com.project.services.impl;

import com.project.dao.impl.AlunoDAOImpl;
import com.project.models.aluno.Aluno;
import com.project.models.aluno.AlunoRequest;
import com.project.models.aluno.AlunoResponse;
import com.project.services.AlunoService;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class AlunoServiceImpl implements AlunoService {

    private ModelMapper mapper = new ModelMapper();
    private AlunoDAOImpl dao = new AlunoDAOImpl();

    @Override
    public List<AlunoResponse> findAll() {
        return dao.findAll().stream()
                .map(e-> mapper.map(e, AlunoResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public AlunoRequest findById(Integer id) {
        Aluno aluno = dao.findById(id);
        if(aluno == null){
            throw new RuntimeException("Usuario não encontrado!");
        }
        return mapper.map(aluno, AlunoRequest.class);
    }

    @Override
    public AlunoRequest findByCpf(String cpf) {
        Aluno aluno = dao.findByCpf(cpf);
        if(aluno == null){
            throw new RuntimeException("Usuario não encontrado!");
        }
        return mapper.map(aluno, AlunoRequest.class);
    }

    @Override
    public AlunoResponse save(AlunoRequest alunoRequest) {
        Aluno aluno = dao.save(mapper.map(alunoRequest, Aluno.class));
        return mapper.map(aluno, AlunoResponse.class);
    }

    @Override
    public AlunoResponse update(Integer id, AlunoRequest alunoRequest) {
        Aluno aluno = dao.update(id, mapper.map(alunoRequest, Aluno.class));
        return mapper.map(aluno, AlunoResponse.class);
    }

    @Override
    public void remove(Integer id) {
        dao.remove(id);
    }
}
