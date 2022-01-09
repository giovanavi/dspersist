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

@NamedQueries({
        @NamedQuery(name = "Aluno.findNomeAndEmail",
                query = "SELECT new trabalho2.entity.Aluno (a.nome, a.email)" +
                        "FROM Aluno a WHERE a.matricula = :matricula"),
        @NamedQuery(name = "Aluno.findNomeAndDisciplinas",
                        query = "SELECT new trabalho2.entity.Aluno(a.nome, d.nome)" +
                                "FROM Aluno a, Disciplina d WHERE a.nome like :nome")
})

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@ToString(exclude = "disciplinas")
@Entity
@Table(name = "alunos")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aluno_id", unique = true)
    @Getter @Setter private Integer id;

//    @Column(unique = true)
    @NonNull @Getter @Setter private String cpf;

    @Column(unique = true)
    @NonNull @Getter @Setter private String matricula;

//    @Column(unique = true)
    @NonNull @Getter @Setter private String nome;

    @Column(unique = true)
    @NonNull @Getter @Setter private String email;

//    @Column(unique = true)
    @Column(name = "data_nascimento")
    @NonNull @Getter @Setter private LocalDate dataNascimento;

    @ManyToMany
    @JoinTable(name = "alunos_disciplinas",
            joinColumns = @JoinColumn(name = "aluno_fk"),
            inverseJoinColumns = @JoinColumn(name = "disciplina_fk"))
    @Getter @Setter private Set<Disciplina> disciplinas;

    @Transient
    @Getter @Setter private long quant;

    public Aluno(String nome, String email){
        this.nome = nome;
        this.email = email;
    }


//    @Transactional
//    public StringBuilder getDisciplinasCursadas(){
//        StringBuilder sb = new StringBuilder();
//        if(!this.disciplinas.isEmpty()) {
//            sb.append(disciplinas.get(0).getNome());
//            for (int i=1; i<this.disciplinas.size(); i++) {
//                sb.append(", "+getDisciplinas().get(i).getNome());
//            }
//        }
//        return sb;
//    }

//    @Transactional
//    public int getQuantDisciplinasCursadas(){
//        int quantidade = 0;
//        if(!this.disciplinas.isEmpty()) {
//            for (Disciplina disciplna : this.disciplinas) {
//                quantidade++;
//            }
//        }else{
//            return 0;
//        }
//        return quantidade;
//    }
}

