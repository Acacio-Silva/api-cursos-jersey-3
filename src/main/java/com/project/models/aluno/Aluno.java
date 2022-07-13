package com.project.models.aluno;

import com.project.models.curso.Curso;
import com.project.models.enums.Sexo;
import com.project.models.enums.Situacao;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_aluno")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;
    private String nome;
    private String cpf;
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    @ManyToOne(cascade = CascadeType.ALL)
    private Curso Curso;
    @Enumerated(EnumType.STRING)
    private Situacao situacao;

}
