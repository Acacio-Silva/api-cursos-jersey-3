package com.project.services;

import com.project.models.professor.Professor;
import com.project.models.professor.ProfessorRequest;
import com.project.models.professor.ProfessorResponse;

import java.util.List;

public interface ProfessorService {

    ProfessorResponse save(ProfessorRequest professorRequest);
    List<ProfessorResponse> findAll();
    ProfessorRequest findById(Integer id);
    ProfessorRequest findByCpf(String cpf);
    ProfessorResponse update(Integer id, ProfessorRequest professorRequest);
    void remove(Integer id);
    ProfessorResponse matriculaProfessor(Integer idProfessor, Integer idCurso);
}
