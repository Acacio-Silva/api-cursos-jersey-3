package com.project.services;

import com.project.models.curso.CursoRequest;
import com.project.models.curso.CursoResponse;

import java.util.List;

public interface CursoService {

    CursoResponse save(CursoRequest cursoRequest);
    List<CursoResponse> findAll();
    CursoRequest findById(Integer id);
    CursoResponse update(Integer id, CursoRequest cursoRequest);
    void remove(Integer id);

}
