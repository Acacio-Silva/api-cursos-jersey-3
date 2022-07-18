package com.project.dao;

import com.project.dao.impl.CursoDAOImpl;
import com.project.models.curso.Curso;
import org.junit.jupiter.api.*;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CursoDAOTest {

    private  Curso curso = new Curso();
    private  Curso saveCurso = new Curso();
    private  CursoDAOImpl cursoDAO = new CursoDAOImpl();

    @BeforeEach
    public  void inicio(){

        curso.setNome("Spring Boot");
        curso.setPreco(new BigDecimal("500"));
        curso.setQuantidadeAlunos(15);

        saveCurso = cursoDAO.save(curso);
        Assertions.assertNotNull(saveCurso);
        Assertions.assertEquals(curso.getNome(), saveCurso.getNome());

    }

    @AfterEach
    public  void finaliza(){
        cursoDAO.remove(saveCurso.getId());
        List<Curso> cursos = new ArrayList<>();
        Assertions.assertEquals(cursos, cursoDAO.findAll());
    }

    @Order(1)
    @Test
    public void buscarPorIdComSucesso(){
        Curso cursoResponse = cursoDAO.findById(saveCurso.getId());
        Assertions.assertNotNull(cursoResponse);
        Assertions.assertEquals("Spring Boot", cursoResponse.getNome());
    }

    @Order(2)
    @Test
    public void buscarPorIdComFalha(){
        Curso cursoResponse = cursoDAO.findById(0);
        Assertions.assertNull(cursoResponse);
    }

    @Order(3)
    @Test
    public void buscarTodosOsCursos(){
        List<Curso> cursos = cursoDAO.findAll();
        Assertions.assertNotNull(cursos);
        Assertions.assertEquals(1, cursos.size());
    }

    @Order(4)
    @Test
    public void updateCurso(){
        Curso cursoUpdate = new Curso();
        cursoUpdate.setNome("Java ee");
        Curso cursoResponseUpdate = cursoDAO.update(curso.getId(), cursoUpdate);

        Assertions.assertNotNull(cursoResponseUpdate);
        Assertions.assertEquals(cursoUpdate.getNome(), cursoResponseUpdate.getNome());
    }

}
