package com.project.dao;

import com.project.models.professor.Professor;

import java.util.List;

public interface ProfessorDAO {

    Professor save(Professor professor);
    List<Professor> findAll();
    Professor findById(Integer id);
    Professor findByCpf(String cpf);
    void remove(Integer id);
    Professor update(Integer id, Professor professor);

}
