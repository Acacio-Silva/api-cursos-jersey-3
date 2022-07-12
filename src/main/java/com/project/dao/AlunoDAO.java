package com.project.dao;

import com.project.models.aluno.Aluno;
import com.project.models.aluno.AlunoRequest;
import com.project.models.aluno.AlunoResponse;

import java.util.List;

public interface AlunoDAO {

    Aluno save(Aluno aluno);
    List<Aluno> findAll();
    Aluno findById(Integer id);
    Aluno findByCpf(String cpf);
    Aluno update(Integer id, Aluno aluno);
    void remove(Integer id);

}
