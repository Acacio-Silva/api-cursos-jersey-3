package com.project.services.impl;

import com.project.dao.impl.CursoDAOImpl;
import com.project.dao.impl.ProfessorDAOImpl;
import com.project.exceptions.DataIntegrityViolation;
import com.project.exceptions.NotFoundException;
import com.project.models.curso.Curso;
import com.project.models.professor.Professor;
import com.project.models.professor.ProfessorRequest;
import com.project.models.professor.ProfessorResponse;
import com.project.services.ProfessorService;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ProfessorServiceImpl implements ProfessorService {

    private CursoDAOImpl cursoDAO = new CursoDAOImpl();
    private ProfessorDAOImpl professorDAO = new ProfessorDAOImpl();
    private ModelMapper mapper = new ModelMapper();

    @Override
    public ProfessorResponse save(ProfessorRequest professorRequest) {
        System.out.println(professorRequest.getTipoContrato());
        Professor professor = professorDAO.save(mapper.map(professorRequest, Professor.class));
        return mapper.map(professor,ProfessorResponse.class);
    }

    @Override
    public List<ProfessorResponse> findAll() {
        return professorDAO.findAll().stream()
                .map(e-> mapper.map(e, ProfessorResponse.class)).collect(Collectors.toList());
    }

    @Override
    public ProfessorRequest findById(Integer id) {
        Professor professor = professorDAO.findById(id);
        if(professor == null){
            throw new NotFoundException("Professor com id: " + id +" não encontrado");
        }
        return mapper.map(professor, ProfessorRequest.class);
    }

    @Override
    public ProfessorRequest findByCpf(String cpf) {
        Professor professor = professorDAO.findByCpf(cpf);
        if(professor == null){
            throw new NotFoundException("Professor com cpf: " + cpf +" não encontrado");
        }
        return mapper.map(professor, ProfessorRequest.class);
    }

    @Override
    public ProfessorResponse update(Integer id, ProfessorRequest professorRequest) {
        findById(id);
        Professor prof = professorDAO.update(id, mapper.map(professorRequest, Professor.class));
        return mapper.map(prof, ProfessorResponse.class);
    }

    @Override
    public void remove(Integer id) {
        Professor professor = professorDAO.findById(id);
        if(professor == null){
            throw new NotFoundException("Professor com id: " + id +" não encontrado");
        }
        professorDAO.remove(id);
    }

    @Override
    public ProfessorResponse matriculaProfessor(Integer idProfessor, Integer idCurso) {

        Curso curso = cursoDAO.findById(idCurso);
        Professor professor = professorDAO.findById(idProfessor);

        if(curso.getProfessor() == null){
            if (professor == null){
                throw new NotFoundException("Professor com id: " + idProfessor +" não encontrado");
            }
            professor.setCurso(curso);
            professorDAO.update(idProfessor, professor);
            return mapper.map(professor, ProfessorResponse.class);
        }
        throw new DataIntegrityViolation("Curso já possui professor!");
    }
}
