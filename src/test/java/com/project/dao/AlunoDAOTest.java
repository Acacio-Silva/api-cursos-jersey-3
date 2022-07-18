package com.project.dao;

import com.project.dao.impl.AlunoDAOImpl;
import com.project.models.aluno.Aluno;
import com.project.models.enums.Sexo;
import com.project.models.enums.Situacao;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AlunoDAOTest {

     AlunoDAOImpl  alunoDAO = new AlunoDAOImpl();
     Aluno aluno = new Aluno();
     Aluno saveAluno = new Aluno();

    @BeforeEach
    public  void IniciaDB(){

        aluno.setNome("Aparecida");
        aluno.setSexo(Sexo.FEMININO);
        aluno.setSituacao(Situacao.ATIVO);
        aluno.setCpf("555.555.444-99");
        saveAluno = alunoDAO.save(aluno);
        Assertions.assertNotNull(saveAluno);
        Assertions.assertEquals("Aparecida", saveAluno.getNome());

    }

    @AfterEach
    public  void FinalizaDB(){
        alunoDAO.remove(saveAluno.getId());
        List<Aluno> alunos = new ArrayList<>();
        Assertions.assertEquals(alunos, alunoDAO.findAll());
    }

    @Test
    @Order(1)
    public void buscarAlunoPorIdSucesso(){
       Aluno findAlunoId = alunoDAO.findById(saveAluno.getId());
        Assertions.assertNotNull(findAlunoId);
        Assertions.assertEquals("Aparecida",findAlunoId.getNome());
    }
    @Order(2)
    @Test
    public void buscarAlunoPorIdFalha(){
        Aluno findAlunoId = alunoDAO.findById(0);
        Assertions.assertNull(findAlunoId);
    }

    @Order(3)
    @Test
    public void buscarAlunoPorCpfSucesso(){
        Aluno findAlunoCpf = alunoDAO.findByCpf(saveAluno.getCpf());
        Assertions.assertNotNull(findAlunoCpf);
        Assertions.assertEquals("Aparecida",findAlunoCpf.getNome());
    }


    @Order(4)
    @Test
    public void BuscarTodosOsAlunos(){
        List<Aluno> alunos = alunoDAO.findAll();
        Assertions.assertNotNull(alunos);
        Assertions.assertEquals(1, alunos.size());
    }

    @Order(5)
    @Test
    public void updateAluno(){
        Aluno alunoUpdate = new Aluno();
        alunoUpdate.setNome("Maria Aparecida");
        Aluno alunoUpdateResponse = alunoDAO.update(aluno.getId(),alunoUpdate);

        Assertions.assertNotNull(alunoUpdateResponse);
        Assertions.assertEquals(alunoUpdate.getNome(), alunoUpdateResponse.getNome());
    }




}
