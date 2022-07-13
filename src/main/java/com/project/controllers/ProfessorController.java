package com.project.controllers;

import com.project.models.curso.CursoRequest;
import com.project.models.professor.ProfessorRequest;
import jakarta.ws.rs.core.Response;

public interface ProfessorController {

    Response save(ProfessorRequest professorRequest);
    Response findByCpf(String cpf);
    Response findById(Integer id);
    Response findAll();
    Response remove(Integer id);
    Response update(Integer id, ProfessorRequest professorRequest);

}
