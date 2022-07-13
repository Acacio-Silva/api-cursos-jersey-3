package com.project.models.curso;

import com.project.models.aluno.Aluno;
import com.project.models.professor.Professor;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;


@Entity
@Table(name = "tb_cursos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;
    private String nome;
    @OneToMany(mappedBy = "curso")
    private List<Aluno> alunos;
    private Integer quantidadeAlunos;
    private BigDecimal preço;
    @OneToMany(mappedBy = "curso")
    private Professor professor;

}
