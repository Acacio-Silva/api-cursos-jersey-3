package com.project.dao;


import com.project.dao.impl.ProfessorDAOImpl;
import com.project.models.aluno.Aluno;
import com.project.models.enums.Sexo;
import com.project.models.professor.Professor;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProfessorDAOTest {

    private  ProfessorDAOImpl professorDAO = new ProfessorDAOImpl();
    private  Professor professor = new Professor();
    private  Professor saveProfessor = new Professor();

    @BeforeEach
    public  void iniciaDB(){
        professor.setNome("Ana");
        professor.setSalario(new BigDecimal("1000"));
        professor.setSexo(Sexo.FEMININO);
        professor.setCpf("555.444.444-88");
        saveProfessor = professorDAO.save(professor);
        Assertions.assertNotNull(saveProfessor);
        Assertions.assertEquals(professor.getNome(), saveProfessor.getNome());
    }

    @AfterEach
    public  void FinalizaDB(){
        professorDAO.remove(saveProfessor.getId());
        List<Aluno> alunos = new ArrayList<>();
        Assertions.assertEquals(alunos, professorDAO.findAll());
    }

    @Test
    public void buscarPorIdSucesso(){
        Professor professorResponse = professorDAO.findById(professor.getId());
        Assertions.assertNotNull(professorResponse);
        Assertions.assertEquals(professor.getNome(), professorResponse.getNome());
    }

    @Order(1)
    @Test
    public void buscarPorIdFalha(){
        Professor professorResponse = professorDAO.findById(0);
        Assertions.assertNull(professorResponse);
    }

    @Order(2)
    @Test
    public void buscarPorCpfSucesso(){
        Professor professorResponse = professorDAO.findByCpf(professor.getCpf());
        Assertions.assertNotNull(professorResponse);
        Assertions.assertEquals(professor.getNome(), professorResponse.getNome());
    }



    @Order(3)
    @Test
    public void BuscarTodosOsProfessores(){
        List<Professor> professores = professorDAO.findAll();
        Assertions.assertNotNull(professores);
        Assertions.assertEquals(1, professores.size());
    }

    @Order(4)
    @Test
    public void updateProfessor(){
        Professor professorUpdate = new Professor();
        professorUpdate.setNome("Ana Maria");
        Professor professorUpdateResponse = professorDAO.update(professor.getId(),professorUpdate);

        Assertions.assertNotNull(professorUpdateResponse);
        Assertions.assertEquals(professorUpdate.getNome(), professorUpdateResponse.getNome());
    }


}
