package trabalho2.entity;

import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

//id, cpf, matrícula, nome, email e data de nascimento.
//Os campos id, código, matrícula e email, considerados individualmente, não devem permitir duplicação,
// ou seja devem ser únicos.
//Existe uma associação entre alunos e disciplinas de modo que
//um aluno pode cursar várias disciplinas e uma disciplinas pode ser cursada por vários alunos.

//Aluno(1-n) -----------------  (n)Disciplina


@NamedQuery(name = "Aluno.findById", query = "select a from Aluno a where a.id = :id")

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "alunos")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    @Getter @Setter private Integer id;

    @NonNull @Getter @Setter private String cpf;

    @Column(unique = true)
    @NonNull @Getter @Setter private String matricula;

    @NonNull @Getter @Setter private String nome;

    @Column(unique = true)
    @NonNull @Getter @Setter private String email;

    @Column(name = "data_nascimento")
    @NonNull @Getter @Setter private LocalDate dataNascimento;

    @ManyToMany
    @Getter @Setter private Set<Disciplina> disciplinas;

    @Transient
    @Getter @Setter private long quant;

    @Override
    public String toString() {
        return "Aluno{" +
                " id: " + id +
                " - cpf: " + cpf +
                " - matrícula : " + matricula +
                " - nome: " + nome +
                " - email: " + email +
                " - data de nascimento: " + dataNascimento + " }";
    }
}

