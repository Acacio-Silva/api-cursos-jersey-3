package com.project;

import com.project.models.aluno.Aluno;
import com.project.models.curso.Curso;
import com.project.models.enums.Sexo;
import com.project.models.enums.Situacao;
import com.project.models.enums.TipoContratoProfessor;
import com.project.models.professor.Professor;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("test")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {

        Aluno aluno01 = new Aluno();
        aluno01.setNome("Francisco");
        aluno01.setSituacao(Situacao.ATIVO);
        aluno01.setCpf("111.111.111-22");
        aluno01.setSexo(Sexo.MASCULINO);

        Aluno aluno02 = new Aluno();
        aluno02.setNome("Francisco");
        aluno02.setSituacao(Situacao.ATIVO);
        aluno02.setCpf("111.111.111-22");
        aluno02.setSexo(Sexo.MASCULINO);

        Professor professor = new Professor();
        professor.setNome("Professor 01");
        professor.setCpf("555.444.666-65");
        professor.setSexo(Sexo.MASCULINO);
        professor.setSalario(new BigDecimal("1500.00"));
        professor.setTipoContrato(TipoContratoProfessor.CLT);
       // professor.setCurso();

        Curso curso01 = new Curso();
        curso01.setNome("Java ee");
        curso01.setPreço(new BigDecimal("100.00"));
        curso01.setQuantidadeAlunos(10);
        curso01.setProfessor(professor);
        curso01.setAlunos(Arrays.asList(aluno01, aluno02));



        return "DB Iniciado com Sucesso!";
    }
}
