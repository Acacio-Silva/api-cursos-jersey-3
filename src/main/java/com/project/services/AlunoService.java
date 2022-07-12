package com.project.services;


import com.project.models.aluno.Aluno;
import com.project.models.aluno.AlunoRequest;
import com.project.models.aluno.AlunoResponse;

import java.util.List;

public interface AlunoService {

    List<AlunoResponse> findAll();
    AlunoRequest findById(Integer id);
    AlunoRequest findByCpf(String cpf);
    AlunoResponse save(AlunoRequest alunoRequest);
    AlunoResponse update(Integer id, AlunoRequest alunoRequest);
    void remove(Integer id);

}
