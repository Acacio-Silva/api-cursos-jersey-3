package com.project.models.curso;

import com.project.models.aluno.Aluno;
import com.project.models.professor.Professor;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CursoRequest {

    @EqualsAndHashCode.Include
    private Integer id;
    private String nome;
    private List<Aluno> alunos;
    private Integer quantidadeAlunos;
    private BigDecimal preco;
    private Professor professor;

}
