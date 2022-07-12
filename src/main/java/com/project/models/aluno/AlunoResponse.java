package com.project.models.aluno;

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
    private String Curso;
}
