package com.project.controllers.impl;

import com.project.controllers.CursoController;
import com.project.models.aluno.AlunoRequest;
import com.project.models.curso.CursoRequest;
import com.project.services.impl.CursoServiceImpl;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("curso")
public class CursoControllerImpl implements CursoController {

    private CursoServiceImpl cursoService = new CursoServiceImpl();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public Response save(CursoRequest cursoRequest) {
        return Response.status(Response.Status.CREATED)
                .entity(cursoService.save(cursoRequest)).build();
    }

    @GET
    @Path("/{id}")
    @Override
    public Response findById(@PathParam("id") Integer id) {
        return Response.ok(cursoService.findById(id)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response findAll() {
        return Response.ok(cursoService.findAll()).build();
    }

    @DELETE
    @Path("/{id}")
    @Override
    public Response remove(@PathParam("id") Integer id) {
        cursoService.remove(id);
        return Response.noContent().build();
    }

    @PUT
    @Path("/{id}")
    @Override
    public Response update(@PathParam("id") Integer id, CursoRequest cursoRequest) {
        return Response.ok(cursoService.update(id, cursoRequest)).build();
    }

}
