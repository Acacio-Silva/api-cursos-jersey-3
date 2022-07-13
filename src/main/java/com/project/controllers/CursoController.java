package com.project.controllers;

import com.project.models.curso.CursoRequest;
import jakarta.ws.rs.core.Response;

public interface CursoController {

    Response save(CursoRequest cursoRequest);
    Response findById(Integer id);
    Response findAll();
    Response remove(Integer id);
    Response update(Integer id, CursoRequest cursoRequest);
}
