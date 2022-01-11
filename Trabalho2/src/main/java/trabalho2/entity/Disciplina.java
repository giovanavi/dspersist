package trabalho2.entity;

import lombok.*;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Set;

//id, código e nome
//Os campos id, código, matrícula e email, considerados individualmente, não devem permitir duplicação,
// ou seja devem ser únicos.
//Existe uma associação entre alunos e disciplinas de modo que
//um aluno pode cursar várias disciplinas e uma disciplinas pode ser cursada por vários alunos.

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "disciplinas")
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    @Getter @Setter private Integer id;

    @Column(unique = true)
    @Getter @Setter private String codigo;

    @Getter @Setter private String nome;

    @ManyToMany(mappedBy = "disciplinas")
    @Getter @Setter private Set<Aluno> alunos;

    @Override
    public String toString() {
        return "Disciplina{" +
                " id: " + id +
                " - código: " + codigo +
                " - nome: " + nome + " }";
    }
}
