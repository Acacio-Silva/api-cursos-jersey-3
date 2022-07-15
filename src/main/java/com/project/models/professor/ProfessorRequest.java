package com.project.models.professor;

import com.project.DTOs.CursoDTO;
import com.project.models.enums.Sexo;
import com.project.models.enums.TipoContratoProfessor;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProfessorRequest {

    @EqualsAndHashCode.Include
    private Integer id;
    private String nome;
    private String cpf;
    private Sexo sexo;
    private CursoDTO Curso;
    private TipoContratoProfessor tipoContrato;
    private BigDecimal salario;

}
