package com.project.controllers.impl;

import com.project.controllers.AlunoController;
import com.project.models.aluno.AlunoRequest;
import com.project.models.curso.CursoRequest;
import com.project.services.impl.AlunoServiceImpl;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("aluno")
public class AlunoControllerImpl implements AlunoController {

    private AlunoServiceImpl alunoService = new AlunoServiceImpl();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public Response save(AlunoRequest alunoRequest) {
        return Response.status(Response.Status.CREATED)
                .entity(alunoService.save(alunoRequest)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response findAll() {
        return Response.ok(alunoService.findAll()).build();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response findById(@PathParam("id") Integer id) {
        return Response.ok(alunoService.findById(id)).build();
    }

    @Path("/buscar/{cpf}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response findByCpf(@PathParam("cpf") String cpf) {
        return Response.ok(alunoService.findByCpf(cpf)).build();
    }
    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response update(@PathParam("id") Integer id, AlunoRequest alunoRequest) {
        return Response.ok(alunoService.update(id, alunoRequest)).build();
    }

    @Path("/{id}")
    @DELETE
    @Override
    public Response remove(@PathParam("id") Integer id) {
        System.out.println(id);
        alunoService.remove(id);
        return Response.noContent().build();
    }

    @Path("/matricula/{idAluno}/{idCurso}")
    @Produces(MediaType.APPLICATION_JSON)
    @PUT
    public Response matricula(@PathParam("idAluno") Integer idAluno,@PathParam("idCurso") Integer idCurso){
        return Response.ok(alunoService.matricula(idAluno, idCurso)).build();
    }
}
