package com.project.models.curso;

import com.project.DTOs.AlunoDTO;
import com.project.DTOs.ProfessorDTO;
import com.project.models.aluno.Aluno;
import com.project.models.aluno.AlunoResponse;
import com.project.models.professor.Professor;
import com.project.models.professor.ProfessorResponse;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CursoResponse {

    @EqualsAndHashCode.Include
    private Integer id;
    private String nome;
    private Integer quantidadeAlunos;
    private BigDecimal preco;
    private ProfessorDTO professor;
    private List<AlunoDTO> alunos;

}
