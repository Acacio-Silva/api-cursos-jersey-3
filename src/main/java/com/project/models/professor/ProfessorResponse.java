package com.project.models.professor;

import com.project.models.curso.Curso;
import com.project.models.enums.Sexo;
import com.project.models.enums.TipoContratoProfessor;
import jakarta.persistence.CascadeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProfessorResponse {

    @EqualsAndHashCode.Include
    private Integer id;
    private String nome;
    private BigDecimal salario;

}
