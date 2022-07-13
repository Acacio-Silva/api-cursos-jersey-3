package com.project.dao;

import com.project.models.curso.Curso;

import java.util.List;

public interface CursoDAO {

    Curso save(Curso curso);
    Curso findById(Integer id);
    List<Curso> findAll();
    Curso update(Integer id, Curso curso);
    void remove(Integer id);

}
