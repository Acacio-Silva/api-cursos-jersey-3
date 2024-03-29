package com.project.models.professor;

import com.project.models.curso.Curso;
import com.project.models.enums.Sexo;
import com.project.models.enums.TipoContratoProfessor;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


@Entity
@Table(name = "tb_professor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Professor{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;
    private String nome;
    private String cpf;
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    @OneToOne
    private Curso Curso;
    @Enumerated(EnumType.STRING)
    private TipoContratoProfessor tipoContrato;
    private BigDecimal salario;


}
