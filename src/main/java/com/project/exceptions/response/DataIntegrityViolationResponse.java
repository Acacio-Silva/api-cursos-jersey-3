package com.project.exceptions.response;

import com.project.exceptions.DataIntegrityViolation;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class DataIntegrityViolationResponse implements ExceptionMapper<DataIntegrityViolation> {

    @Override
    public Response toResponse(DataIntegrityViolation exception) {
        return Response.status(Response.Status.BAD_REQUEST)
                .type(MediaType.APPLICATION_JSON).entity(exception.getMessage()).build();
    }
}
