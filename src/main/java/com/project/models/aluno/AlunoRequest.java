package com.project.models.aluno;

import com.project.models.curso.Curso;
import com.project.models.enums.Sexo;
import com.project.models.enums.Situacao;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AlunoRequest {

    @EqualsAndHashCode.Include
    private Integer id;
    private String nome;
    private String cpf;
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    private Curso Curso;
    @Enumerated(EnumType.STRING)
    private Situacao situacao;
}
