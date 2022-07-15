package com.project.dao;


import com.project.dao.impl.ProfessorDAOImpl;
import com.project.models.aluno.Aluno;
import com.project.models.enums.Sexo;
import com.project.models.professor.Professor;
import jakarta.persistence.NoResultException;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAOTest {

    private static ProfessorDAOImpl professorDAO = new ProfessorDAOImpl();
    private static Professor professor = new Professor();
    private static Professor saveProfessor = new Professor();

    @BeforeClass
    public static void iniciaDB(){
        professor.setNome("Ana");
        professor.setSalario(new BigDecimal("1000"));
        professor.setSexo(Sexo.FEMININO);
        professor.setCpf("555.444.444-88");
        saveProfessor = professorDAO.save(professor);
        Assert.assertNotNull(saveProfessor);
        Assert.assertEquals(professor.getNome(), saveProfessor.getNome());
    }

    @AfterClass
    public static void FinalizaDB(){
        professorDAO.remove(saveProfessor.getId());
        List<Aluno> alunos = new ArrayList<>();
        Assert.assertEquals(alunos, professorDAO.findAll());
    }

    @Test
    public void buscarPorIdSucesso(){
        Professor professorResponse = professorDAO.findById(professor.getId());
        Assert.assertNotNull(professorResponse);
        Assert.assertEquals(professor.getNome(), professorResponse.getNome());
    }

    @Test
    public void buscarPorIdFalha(){
        Professor professorResponse = professorDAO.findById(0);
        Assert.assertNull(professorResponse);
    }

    @Test
    public void buscarPorCpfSucesso(){
        Professor professorResponse = professorDAO.findByCpf(professor.getCpf());
        Assert.assertNotNull(professorResponse);
        Assert.assertEquals(professor.getNome(), professorResponse.getNome());
    }

    @Test(expected = NoResultException.class)
    public void buscarPorCpfFalha(){
        Professor professorResponse = professorDAO.findByCpf("");
        Assert.assertNull(professorResponse);
    }

    @Test
    public void BuscarTodosOsProfessores(){
        List<Professor> professores = professorDAO.findAll();
        Assert.assertNotNull(professores);
        Assert.assertEquals(1, professores.size());
    }

    @Test
    public void updateProfessor(){
        Professor professorUpdate = new Professor();
        professorUpdate.setNome("Ana Maria");
        Professor professorUpdateResponse = professorDAO.update(professor.getId(),professorUpdate);

        Assert.assertNotNull(professorUpdateResponse);
        Assert.assertEquals(professorUpdate.getNome(), professorUpdateResponse.getNome());
    }


}
