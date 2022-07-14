package com.project.DTOs;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AlunoDTO {

    @EqualsAndHashCode.Include
    private Integer id;
    private String nome;
}
