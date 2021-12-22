package entity;

import lombok.*;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

//id, cpf, matrícula, nome, email e data de nascimento.
//Os campos id, código, matrícula e email, considerados individualmente, não devem permitir duplicação,
// ou seja devem ser únicos.
//Existe uma associação entre alunos e disciplinas de modo que
//um aluno pode cursar várias disciplinas e uma disciplinas pode ser cursada por vários alunos.

//Aluno(1-n) -----------------  (n)Disciplina

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "alunos")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    @Getter @Setter private Integer aluno_id;

//    @Column(unique = true)
    @Getter @Setter private String cpf;

    @Column(unique = true)
    @Getter @Setter private String matricula;

//    @Column(unique = true)
    @Getter @Setter private String nome;

    @Column(unique = true)
    @Getter @Setter private String email;

//    @Column(unique = true)
    @Column(name = "data_nascimento")
    @Getter @Setter private Date dataNascimento;

    @ManyToMany(mappedBy = "alunos")
    @Getter @Setter private List<Disciplina> disciplinas;

    private int getQuantDisciplinasCursadas(Integer id){
        int quantidade = 0;
        for(Disciplina disciplna: this.disciplinas) {
            quantidade++;
        }
        return quantidade;
    }
}
