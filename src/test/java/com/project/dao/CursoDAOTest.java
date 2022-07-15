package com.project.dao;

import com.project.dao.impl.CursoDAOImpl;
import com.project.models.aluno.Aluno;
import com.project.models.curso.Curso;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CursoDAOTest {

    private static Curso curso = new Curso();
    private static Curso saveCurso = new Curso();
    private static CursoDAOImpl cursoDAO = new CursoDAOImpl();

    @BeforeClass
    public static void inicio(){

        curso.setNome("Spring Boot");
        curso.setPreco(new BigDecimal("500"));
        curso.setQuantidadeAlunos(15);

        saveCurso = cursoDAO.save(curso);
        Assert.assertNotNull(saveCurso);
        Assert.assertEquals(curso.getNome(), saveCurso.getNome());

    }

    @AfterClass
    public static void finaliza(){
        cursoDAO.remove(saveCurso.getId());
        List<Curso> cursos = new ArrayList<>();
        Assert.assertEquals(cursos, cursoDAO.findAll());
    }

    @Test
    public void buscarPorIdComSucesso(){
        Curso cursoResponse = cursoDAO.findById(saveCurso.getId());
        Assert.assertNotNull(cursoResponse);
        Assert.assertEquals("Spring Boot", cursoResponse.getNome());
    }

    @Test
    public void buscarPorIdComFalha(){
        Curso cursoResponse = cursoDAO.findById(0);
        Assert.assertNull(cursoResponse);
    }

    @Test
    public void buscarTodosOsCursos(){
        List<Curso> cursos = cursoDAO.findAll();
        Assert.assertNotNull(cursos);
        Assert.assertEquals(1, cursos.size());
    }

    @Test
    public void updateCurso(){
        Curso cursoUpdate = new Curso();
        cursoUpdate.setNome("Java ee");
        Curso cursoResponseUpdate = cursoDAO.update(curso.getId(), cursoUpdate);

        Assert.assertNotNull(cursoResponseUpdate);
        Assert.assertEquals(cursoUpdate.getNome(), cursoResponseUpdate.getNome());
    }

}
