package com.project.models.aluno;

import com.project.models.curso.Curso;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AlunoResponse {

    @EqualsAndHashCode.Include
    private Integer id;
    private String nome;
    private String cpf;

}
