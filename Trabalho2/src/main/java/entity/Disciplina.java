package entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

//id, código e nome
//Os campos id, código, matrícula e email, considerados individualmente, não devem permitir duplicação,
// ou seja devem ser únicos.
//Existe uma associação entre alunos e disciplinas de modo que
//um aluno pode cursar várias disciplinas e uma disciplinas pode ser cursada por vários alunos.

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "disciplinas")
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    @Getter @Setter private Integer disc_id;

    @Column(unique = true)
    @Getter @Setter private String codigo;

    @Getter @Setter private String nome;

    @ManyToMany
    @JoinTable(name = "aluno_disciplina",
            joinColumns = @JoinColumn(name = "disc_id"),
            inverseJoinColumns = @JoinColumn(name = "aluno_id"))
    @Getter @Setter private List<Aluno> alunos;

}
