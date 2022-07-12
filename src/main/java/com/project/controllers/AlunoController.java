package com.project.controllers;

import com.project.models.aluno.AlunoRequest;
import jakarta.ws.rs.core.Response;

public interface AlunoController {

    Response save(AlunoRequest alunoRequest);
    Response findAll();
    Response findById(Integer id);
    Response findByCpf(String cpf);
    Response update(Integer id, AlunoRequest alunoRequest);
    Response remove(Integer id);


}
