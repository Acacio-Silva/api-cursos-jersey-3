package com.project.dao;

import com.project.dao.impl.AlunoDAOImpl;
import com.project.dao.impl.CursoDAOImpl;
import com.project.dao.impl.ProfessorDAOImpl;
import com.project.models.aluno.Aluno;
import com.project.models.curso.Curso;
import com.project.models.enums.Sexo;
import com.project.models.enums.Situacao;
import com.project.models.professor.Professor;
import jakarta.persistence.NoResultException;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AlunoDAOTest extends Connection{

    static AlunoDAOImpl  alunoDAO = new AlunoDAOImpl();
    static Aluno aluno = new Aluno();
    static Aluno saveAluno = new Aluno();

    @BeforeClass
    public static void IniciaDB(){

        aluno.setNome("Aparecida");
        aluno.setSexo(Sexo.FEMININO);
        aluno.setSituacao(Situacao.ATIVO);
        aluno.setCpf("555.555.444-99");
        saveAluno = alunoDAO.save(aluno);
        Assert.assertNotNull(saveAluno);
        Assert.assertEquals("Aparecida", saveAluno.getNome());

    }

    @AfterClass
    public static void FinalizaDB(){
        alunoDAO.remove(saveAluno.getId());
        List<Aluno> alunos = new ArrayList<>();
        Assert.assertEquals(alunos, alunoDAO.findAll());
    }

    @Test
    public void buscarAlunoPorIdSucesso(){
       Aluno findAlunoId = alunoDAO.findById(saveAluno.getId());
        Assert.assertNotNull(findAlunoId);
        Assert.assertEquals("Aparecida",findAlunoId.getNome());
    }
    @Test
    public void buscarAlunoPorIdFalha(){
        Aluno findAlunoId = alunoDAO.findById(0);
        Assert.assertNull(findAlunoId);
    }

    @Test
    public void buscarAlunoPorCpfSucesso(){
        Aluno findAlunoCpf = alunoDAO.findByCpf(saveAluno.getCpf());
        Assert.assertNotNull(findAlunoCpf);
        Assert.assertEquals("Aparecida",findAlunoCpf.getNome());
    }
    @Test(expected = NoResultException.class)
    public void buscarAlunoPorCpfFalha(){
        Aluno findAlunoCpf = alunoDAO.findByCpf("");
        Assert.assertNull(findAlunoCpf);
    }

    @Test
    public void BuscarTodosOsAlunos(){
        List<Aluno> alunos = alunoDAO.findAll();
        Assert.assertNotNull(alunos);
        Assert.assertEquals(1, alunos.size());
    }

    @Test
    public void updateAluno(){
        Aluno alunoUpdate = new Aluno();
        alunoUpdate.setNome("Maria Aparecida");
        Aluno alunoUpdateResponse = alunoDAO.update(aluno.getId(),alunoUpdate);

        Assert.assertNotNull(alunoUpdateResponse);
        Assert.assertEquals(alunoUpdate.getNome(), alunoUpdateResponse.getNome());
    }




}
