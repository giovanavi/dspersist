package trabalho2.entity;

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
@ToString(exclude = "alunos")
@Entity
@Table(name = "disciplinas")
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "disciplina_id", unique = true)
    @Getter @Setter private Integer id;

    @Column(unique = true)
    @Getter @Setter private String codigo;

    @Getter @Setter private String nome;

    @ManyToMany
    @JoinTable(name = "aluno_disciplina",
            joinColumns = @JoinColumn(name = "disciplina_id"),
            inverseJoinColumns = @JoinColumn(name = "aluno_id"))
    @Getter @Setter private List<Aluno> alunos;

    @Override
    public String toString() {
        return  " id: " + id + " - código: " + codigo + " - nome: " +nome ;
    }

    public StringBuilder getAlunosMatriculados(){
        StringBuilder sb = new StringBuilder();
        if(this.alunos!=null) {
            sb.append(alunos.get(0));
            for (int i=1; i<this.alunos.size(); i++) {
                sb.append(", "+getAlunos().get(i).getNome());
            }
        }
        return sb;
    }
}
