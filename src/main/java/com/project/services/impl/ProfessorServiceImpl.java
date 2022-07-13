package com.project.services.impl;

import com.project.dao.ProfessorDAO;
import com.project.dao.impl.ProfessorDAOImpl;
import com.project.models.professor.Professor;
import com.project.models.professor.ProfessorRequest;
import com.project.models.professor.ProfessorResponse;
import com.project.services.ProfessorService;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ProfessorServiceImpl implements ProfessorService {

    private ProfessorDAOImpl professorDAO = new ProfessorDAOImpl();
    private ModelMapper mapper = new ModelMapper();

    @Override
    public ProfessorResponse save(ProfessorRequest professorRequest) {
        Professor professor = professorDAO.save(mapper.map(professorRequest, Professor.class));
        return mapper.map(professor,ProfessorResponse.class);
    }

    @Override
    public List<ProfessorResponse> findAll() {
        System.out.println("--> find service <---");
        return professorDAO.findAll().stream()
                .map(e-> mapper.map(e, ProfessorResponse.class)).collect(Collectors.toList());
    }

    @Override
    public ProfessorRequest findById(Integer id) {
        Professor professor = professorDAO.findById(id);
        if(professor == null){
            throw new RuntimeException("Professor não encontrado");
        }
        return mapper.map(professor, ProfessorRequest.class);
    }

    @Override
    public ProfessorRequest findByCpf(String cpf) {
        Professor professor = professorDAO.findByCpf(cpf);
        if(professor == null){
            throw new RuntimeException("Professor não encontrado");
        }
        return mapper.map(professor, ProfessorRequest.class);
    }

    @Override
    public ProfessorResponse update(Integer id, ProfessorRequest professorRequest) {
        Professor prof = professorDAO.update(id, mapper.map(professorRequest, Professor.class));
        return mapper.map(prof, ProfessorResponse.class);
    }

    @Override
    public void remove(Integer id) {
        Professor professor = professorDAO.findById(id);
        if(professor == null){
            throw new RuntimeException("Professor não encontrado");
        }
        professorDAO.remove(id);
    }
}
