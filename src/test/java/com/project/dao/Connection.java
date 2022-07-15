package com.project.dao;

import com.project.dao.impl.AlunoDAOImpl;
import com.project.dao.impl.CursoDAOImpl;
import com.project.dao.impl.ProfessorDAOImpl;
import com.project.models.aluno.Aluno;
import com.project.models.curso.Curso;
import com.project.models.enums.Sexo;
import com.project.models.enums.Situacao;
import com.project.models.enums.TipoContratoProfessor;
import com.project.models.professor.Professor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import java.math.BigDecimal;


public class Connection {
/*
    protected static EntityManagerFactory entityManagerFactory;
    protected  EntityManager entityManager;

    Professor professor;
    static Aluno aluno01;
    static Aluno aluno02;
    static Curso curso01;



    @BeforeClass
    public static void setUpBeforeClass(){
        entityManagerFactory = Persistence
                .createEntityManagerFactory("crud");


        AlunoDAOImpl alunoDAO = new AlunoDAOImpl();
        ProfessorDAOImpl professorDAO = new ProfessorDAOImpl();
        CursoDAOImpl cursoDAO = new CursoDAOImpl();

        curso01 = new Curso();
        curso01.setId(1);
        curso01.setNome("Java ee");
        curso01.setPreco(new BigDecimal("100.00"));
        curso01.setQuantidadeAlunos(10);

        cursoDAO.save(curso01);

        Aluno aluno01 = new Aluno();
        aluno01.setId(1);
        aluno01.setNome("Francisco");
        aluno01.setSituacao(Situacao.ATIVO);
        aluno01.setCpf("111.111.111-22");
        aluno01.setSexo(Sexo.MASCULINO);
        aluno01.setCurso(curso01);

        alunoDAO.save(aluno01);

        Aluno aluno02 = new Aluno();
        aluno02.setId(2);
        aluno02.setNome("Acácio");
        aluno02.setSituacao(Situacao.ATIVO);
        aluno02.setCpf("222.222.222-33");
        aluno02.setSexo(Sexo.MASCULINO);
        aluno02.setCurso(curso01);

        alunoDAO.save(aluno02);


        Professor professor = new Professor();
        professor.setId(1);
        professor.setNome("Professor 01");
        professor.setCpf("555.444.666-65");
        professor.setSexo(Sexo.MASCULINO);
        professor.setSalario(new BigDecimal("1500.00"));
        professor.setTipoContrato(TipoContratoProfessor.CLT);
        professor.setCurso(curso01);

        professorDAO.save(professor);



    }

    @Before
    public void setUp(){
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterClass
    public static void tearDownAfterClass(){
        AlunoDAOImpl alunoDAO = new AlunoDAOImpl();
        ProfessorDAOImpl professorDAO = new ProfessorDAOImpl();
        CursoDAOImpl cursoDAO = new CursoDAOImpl();

        cursoDAO.remove(curso01.getId());

        entityManagerFactory.close();
    }

    @After
    public void tearDown(){
        entityManager.close();
    }
*/
}
