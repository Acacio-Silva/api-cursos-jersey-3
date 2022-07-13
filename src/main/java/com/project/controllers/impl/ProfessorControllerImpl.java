package com.project.controllers.impl;

import com.project.controllers.ProfessorController;
import com.project.models.professor.ProfessorRequest;
import com.project.services.impl.ProfessorServiceImpl;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("professor")
public class ProfessorControllerImpl implements ProfessorController {

    private ProfessorServiceImpl professorService = new ProfessorServiceImpl();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public Response save(ProfessorRequest professorRequest) {
        return Response.status(Response.Status.CREATED)
                .entity(professorService.save(professorRequest)).build();
    }

    @Path("/buscar/{cpf}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response findByCpf(@PathParam("cpf") String cpf) {
        return Response.ok(professorService.findByCpf(cpf)).build();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response findById(@PathParam("id") Integer id) {
        return Response.ok(professorService.findById(id)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response findAll() {
        return Response.ok(professorService.findAll()).build();
    }

    @DELETE
    @Path("/{id}")
    @Override
    public Response remove(@PathParam("id") Integer id) {
        professorService.remove(id);
        return Response.noContent().build();
    }

    @Path("/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public Response update(@PathParam("id") Integer id, ProfessorRequest professorRequest) {
        return Response.ok(professorService.update(id, professorRequest)).build();
    }
}
