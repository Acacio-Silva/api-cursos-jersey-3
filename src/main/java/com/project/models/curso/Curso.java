package com.project.models.curso;

import com.project.models.aluno.Aluno;
import com.project.models.aluno.AlunoResponse;
import com.project.models.professor.Professor;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.math.BigDecimal;
import java.util.List;


@Entity
@Table(name = "tb_cursos")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;
    private String nome;
    @JsonbTransient
    @OneToMany(mappedBy = "Curso", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Aluno> alunos;
    private Integer quantidadeAlunos;
    private BigDecimal preco;
    @JsonbTransient
    @OneToOne(mappedBy = "Curso", cascade = CascadeType.ALL)
    private Professor professor;

}
