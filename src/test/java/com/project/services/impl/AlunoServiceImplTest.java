package com.project.services.impl;

import com.project.dao.impl.AlunoDAOImpl;
import com.project.dao.impl.CursoDAOImpl;
import com.project.exceptions.DataIntegrityViolation;
import com.project.exceptions.NotFoundException;
import com.project.models.aluno.Aluno;
import com.project.models.aluno.AlunoRequest;
import com.project.models.aluno.AlunoResponse;
import com.project.models.curso.Curso;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;


class AlunoServiceImplTest {


    @InjectMocks
    private AlunoServiceImpl alunoService;

    @Mock
    private AlunoDAOImpl alunoDAO;

    @Mock
    private CursoDAOImpl cursoDAO;

    @Mock
    private ModelMapper mapper;

    private Aluno aluno;
    private AlunoRequest alunoRequest;
    private AlunoResponse alunoResponse;
    private Curso curso;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        iniciaAlunos();
    }

    @Test
    void findAllAlunos() {
        Mockito.when(alunoDAO.findAll()).thenReturn(List.of(aluno));
        Mockito.when(mapper.map(Mockito.any(), Mockito.any())).thenReturn(alunoResponse);
        List<AlunoResponse> alunos = alunoService.findAll();
        Assertions.assertNotNull(alunos);
        Assertions.assertEquals(1, alunos.size());
        Assertions.assertEquals(AlunoResponse.class, alunos.get(0).getClass());
        Assertions.assertEquals(1, alunos.get(0).getId());
        Assertions.assertEquals("acacio", alunos.get(0).getNome());
        Assertions.assertEquals("333.333.333-33", alunos.get(0).getCpf());

    }

    @Test
    void findById() {
        Mockito.when(alunoDAO.findById(Mockito.anyInt())).thenReturn(aluno);
        Mockito.when(mapper.map(Mockito.any(), Mockito.any())).thenReturn(alunoRequest);
        AlunoRequest alunoServiceById = alunoService.findById(1);
        Assertions.assertNotNull(alunoServiceById);
        Assertions.assertEquals("acacio", alunoServiceById.getNome());

    }

    @Test
    void findByIdObjectNotFound(){
        Mockito.when(alunoDAO.findById(Mockito.anyInt()))
                .thenThrow(new NotFoundException("Aluno não encontrado!"));
        try {
            alunoService.findById(1);
        }catch (Exception ex){
            Assertions.assertEquals(NotFoundException.class, ex.getClass());
            Assertions.assertEquals("Aluno não encontrado!", ex.getMessage());
        }
    }

    @Test
    void findByCpf() {
        Mockito.when(alunoDAO.findByCpf(Mockito.anyString())).thenReturn(aluno);
        Mockito.when(mapper.map(Mockito.any(), Mockito.any())).thenReturn(alunoRequest);
        AlunoRequest alunoServiceByCpf = alunoService.findByCpf("333.333.333-33");
        Assertions.assertNotNull(alunoServiceByCpf);
        Assertions.assertEquals("acacio", alunoServiceByCpf.getNome());
    }

    @Test
    void findByCpfObjectNotFound(){
        Mockito.when(alunoDAO.findByCpf(Mockito.anyString()))
                .thenThrow(new NotFoundException("Aluno não encontrado!"));
        try {
            alunoService.findByCpf("");
        }catch (Exception ex){
            Assertions.assertEquals(NotFoundException.class, ex.getClass());
            Assertions.assertEquals("Aluno não encontrado!", ex.getMessage());
        }
    }

    @Test
    void AlunoSalvo() {
        Mockito.when(alunoDAO.save(Mockito.any())).thenReturn(aluno);
        Mockito.when(mapper.map(Mockito.any(Aluno.class), Mockito.any())).thenReturn(alunoResponse);

        AlunoResponse response = alunoService.save(alunoRequest);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(1, response.getId());
        Assertions.assertEquals("acacio", response.getNome());
    }

    @Test
    void updateAlunoSucesso() {

    }

    @Test
    void removeAlunoSucesso() {

        Mockito.when(alunoDAO.findById(Mockito.anyInt())).thenReturn(aluno);
        Mockito.doNothing().when(alunoDAO).remove(Mockito.anyInt());
        alunoService.remove(1);
        Mockito.verify(alunoDAO, Mockito.times(1)).remove(Mockito.anyInt());

    }

    @Test
    void removeAlunoErroNaoEncontrado() {
        Mockito.when(alunoDAO.findById(Mockito.anyInt()))
                .thenThrow(new NotFoundException("Aluno não encontrado"));
        try{
           alunoService.remove(1);
        }catch (Exception ex){
            Assertions.assertEquals(NotFoundException.class, ex.getClass());
            Assertions.assertEquals("Aluno não encontrado", ex.getMessage());
        }

    }

    @Test
    void matricula() {
        Mockito.when(alunoDAO.findById(Mockito.anyInt())).thenReturn(aluno);
        Mockito.when(cursoDAO.findById(Mockito.anyInt())).thenReturn(curso);
        Mockito.when(alunoDAO.update(Mockito.anyInt(), Mockito.any())).thenReturn(aluno);

        Mockito.when(mapper.map(Mockito.any(), Mockito.any())).thenReturn(alunoRequest);

        AlunoRequest response = alunoService.matricula(1, 1);

        Assertions.assertEquals(alunoRequest.getClass(), response.getClass());
        Assertions.assertEquals(1, response.getId());
    }

    @Test
    void matriculaSemUsuario(){
        Mockito.when(alunoDAO.findById(Mockito.anyInt()))
                .thenThrow(new NotFoundException("aluno não encontrado"));

        try {
            alunoService.findById(1);
        }catch (Exception ex){
            Assertions.assertEquals(NotFoundException.class, ex.getClass());
            Assertions.assertEquals("aluno não encontrado", ex.getMessage());
        }
    }

    @Test
    void turmaComCapacidadeMaxima(){
        Mockito.when(cursoDAO.findById(Mockito.anyInt()))
                .thenThrow(new DataIntegrityViolation("Turma com capacidade maxima preenchida"));
        try{
            alunoService.matricula(1,1);
        }catch (Exception ex){
            Assertions.assertEquals(DataIntegrityViolation.class, ex.getClass());
            Assertions.assertEquals("Turma com capacidade maxima preenchida", ex.getMessage());
        }
    }

    private void iniciaAlunos(){
         aluno = new Aluno();
         aluno.setId(1);
         aluno.setNome("acacio");
         aluno.setCpf("333.333.333-33");

        alunoRequest = new AlunoRequest();
        alunoRequest.setId(1);
        alunoRequest.setNome("acacio");
        alunoRequest.setCpf("333.333.333-33");

        alunoResponse = new AlunoResponse();
        alunoResponse.setId(1);
        alunoResponse.setNome("acacio");
        alunoResponse.setCpf("333.333.333-33");

        curso = new Curso();
        curso.setId(1);
        curso.setNome("Java ee");
        curso.setQuantidadeAlunos(2);
        curso.setPreco(new BigDecimal("100"));
        curso.setAlunos(Arrays.asList(aluno));


    }
}